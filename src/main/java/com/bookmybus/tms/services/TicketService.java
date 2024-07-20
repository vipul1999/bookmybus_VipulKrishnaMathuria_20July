package com.bookmybus.tms.services;

import com.bookmybus.tms.converters.RouteBusConverter;
import com.bookmybus.tms.database.entity.*;
import com.bookmybus.tms.database.repository.*;
import com.bookmybus.tms.dtos.RouteBusDTO;
import com.bookmybus.tms.dtos.TicketBookingRequest;
import com.bookmybus.tms.dtos.TicketDto;
import com.bookmybus.tms.enums.TicketStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private AvailabilityService availabilityService;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RouteBusRepository routeBusRepository;

    @Autowired
    private CustomerRepository customerRepository;

    //search for the buses
    //view bus schedules
    //select seats
        //will not define seat class, but will manage capacity
    //make reservations

    // find a route_id by input of from and to
        //tripRepository.findBySourceAndDestination

    // find all the buses on that route
        //show user time of all the buses
    //when click on one bus, show the status of bus seats
    //after that book one ticket for that user after payment

//    public Ticket bookTicket(Long routeId, Long userId, LocalDate date) {
//        Route route = routeRepository.findById(routeId)
//                .orElseThrow(() -> new RuntimeException("Route not found"));
//
//        Availability availability = availabilityService.getAvailability(routeId, date);
//        if (availability.getSeatsFilled() >= availability.getTotalSeats()) {
//            throw new RuntimeException("No available seats");
//        }
//
//        availability.setSeatsFilled(availability.getSeatsFilled() + 1);
//        availabilityService.updateAvailability(route, date, availability.getSeatsFilled());
//
//        Ticket ticket = new Ticket();
//        ticket.setRoute(route);
//        ticket.setCustomer();
//        ticket.setBookingTime(LocalDateTime.now());
//        ticket.setTicketStatus(TicketStatus.BOOKED);
//        return ticketRepository.save(ticket);
//    }


    public TicketDto bookTicket(TicketBookingRequest bookingRequest) {
        Optional<RouteBus> routeBusOptional = routeBusRepository.findById(bookingRequest.getRouteBusId());

        if (!routeBusOptional.isPresent()) {
            throw new RuntimeException("RouteBus with ID " + bookingRequest.getRouteBusId() + " not found.");
        }

        RouteBus routeBus = routeBusOptional.get();
        List<String> bookedSeats = routeBus.getBookedSeats();
        String seatNumber = String.valueOf(bookingRequest.getSeatNumber());

        if (bookedSeats.contains(seatNumber)) {
            throw new RuntimeException("Seat " + seatNumber + " is already booked.");
        }

        Customer customer = customerRepository.findById(bookingRequest.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer with ID " + bookingRequest.getCustomerId() + " not found."));

        // Create and save the ticket
        Ticket ticket = new Ticket();
        ticket.setRouteBus(routeBus);
        ticket.setSeatNumber(bookingRequest.getSeatNumber());
        ticket.setCustomer(customer);
        ticket.setBookingTime(new Date());
        ticket.setTicketStatus(TicketStatus.BOOKED);
        ticket.setRoute(routeBus.getRoute());  // Assuming Route is set in RouteBus
        ticket.setCreatedAt(new Date());
        ticket.setCreatedBy("system");
        ticket.setUpdatedAt(new Date());
        ticket.setUpdatedBy("system");
        ticket.setRequestId("dummy_id");
        ticketRepository.save(ticket);

        // Update booked seats in routeBus
        bookedSeats.add(seatNumber);
        routeBus.setBookedSeats(bookedSeats);
        routeBusRepository.save(routeBus);

        return mapToDto(ticket);
    }

    private TicketDto mapToDto(Ticket ticket) {
        return new TicketDto(
                ticket.getId().longValue(),
                ticket.getRouteBus().getBus().getVehicleRegistrationNumber(),
                ticket.getSeatNumber(),
                ticket.getBookingTime(),
                ticket.getTicketStatus()
        );
    }
    public Ticket bookTicket(Long customerId, Long routeBusId, int seatNumber) {
        // Fetch the customer
        Customer customer = (Customer) userRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Fetch the RouteBus entity
        RouteBus routeBus = routeBusRepository.findById(routeBusId)
                .orElseThrow(() -> new RuntimeException("RouteBus not found"));

        // Check if the seat is already booked
        boolean isSeatBooked = ticketRepository.existsByRouteBusAndSeatNumber(routeBus, seatNumber);
        if (isSeatBooked) {
            throw new RuntimeException("Seat is already booked");
        }

        // Create and save the ticket
        Ticket ticket = new Ticket();
        ticket.setCustomer(customer);
        ticket.setRouteBus(routeBus);
        ticket.setSeatNumber(seatNumber);
        ticket.setBookingTime(new Date());
        return ticketRepository.save(ticket);
    }

    @Autowired
    RouteService routeService;


    public List<RouteBusDTO> showBusAvailability(@RequestParam String from,
                                             @RequestParam String to,
                                             @RequestParam String date){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Adjust the format as needed
        Date parsedDate;

        try {
            // Parse the date string into a Date object
            parsedDate = dateFormat.parse(date);
        } catch (ParseException e) {
            // Handle the case where the date string is not in the expected format
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd.", e);
        }

        Route route = routeService.getRouteBySourceAndDestination(from,to);
        List<RouteBus> routeBusList = availabilityService.getAvailableBuses(route.getId(),parsedDate);
//        System.out.println(routeBusList.toString());
        return RouteBusConverter.convertToDTOList(routeBusList);


    }

}
