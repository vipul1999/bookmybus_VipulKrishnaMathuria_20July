package com.bookmybus.tms.services;

import com.bookmybus.tms.converters.RouteBusConverter;
import com.bookmybus.tms.database.entity.RouteBus;
import com.bookmybus.tms.database.repository.RouteBusRepository;
import com.bookmybus.tms.database.repository.RouteRepository;
import com.bookmybus.tms.database.repository.TicketRepository;
import com.bookmybus.tms.dtos.RouteBusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AvailabilityService {
    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private RouteBusRepository routeBusRepository;
    @Autowired
    private TicketRepository ticketRepository;


//    public Availability getAvailability(Long routeId, LocalDate date) {
//        Route route = routeRepository.findById(routeId)
//                .orElseThrow(() -> new RuntimeException("Route not found"));
//
//        return availabilityRepository.findByRouteAndDate(route, date)
//                .orElseThrow(() -> new RuntimeException("No availability found for this date"));
//    }
//
//    public Availability updateAvailability(Route route, LocalDate date, int seatsFilled) {
//        Availability availability = availabilityRepository.findByRouteAndDate(route, date)
//                .orElseGet(() -> new Availability(route, date, route.getBus().getTotalSeats(), 0));
//
//        availability.setSeatsFilled(seatsFilled);
//        return availabilityRepository.save(availability);
//    }



//    public List<BusAvailability> getAvailableBuses(Long routeId, LocalDate date) {
//        // Fetch RouteBus entities for the given route and date
//        List<RouteBus> routeBuses = routeBusRepository.findByRouteAndDate(routeId, date);
//
//        // Calculate available seats for each bus
//        List<BusAvailability> busAvailabilities = new ArrayList<>();
//        for (RouteBus routeBus : routeBuses) {
//            Bus bus = routeBus.getBus();
//            int totalSeats = bus.getCapacity();
//            int bookedSeats = ticketRepository.countByRouteBus(routeBus);
//            int availableSeats = totalSeats - bookedSeats;
//            busAvailabilities.add(new BusAvailability(bus, availableSeats));
//        }
//        return busAvailabilities;
//    }

    public List<String> getAvailableSeats(Long routeBusId) {
        RouteBus routeBus = routeBusRepository.findById(routeBusId)
                .orElseThrow(() -> new RuntimeException("RouteBus not found"));

        // Assuming seat numbers are in a fixed range or format
        List<String> allSeats = generateAllSeats(); // This method generates a list of all possible seat numbers
        List<String> bookedSeats = routeBus.getBookedSeats();

        // Find available seats by excluding booked ones
        List<String> availableSeats = new ArrayList<>();
        for (String seat : allSeats) {
            if (!bookedSeats.contains(seat)) {
                availableSeats.add(seat);
            }
        }

        return availableSeats;
    }

    private List<String> generateAllSeats() {
        // Example seat generation: Seats 1 to 50
        List<String> seats = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            seats.add(String.valueOf(i));
        }
        return seats;
    }

    public List<RouteBus> getAvailableBuses(Long routeId, Date date) {
        List<RouteBus> routeBuses = routeBusRepository.findByRouteIdAndDate(routeId, date);
        return routeBuses;

    }
}
