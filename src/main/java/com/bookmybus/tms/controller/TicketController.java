package com.bookmybus.tms.controller;

import com.bookmybus.tms.database.entity.RouteBus;
import com.bookmybus.tms.database.entity.Ticket;
import com.bookmybus.tms.dtos.*;
import com.bookmybus.tms.services.RouteBusService;
import com.bookmybus.tms.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/buses")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private RouteBusService routeBusService;

    @GetMapping("/search")
    public List<RouteBusDTO> searchBuses(
            @RequestParam String to,
            @RequestParam String from,
            @RequestParam String date) {
        return ticketService.showBusAvailability(from, to, date);
    }

    @GetMapping("/{bus_id}/available-seats")
    public List<SeatDTO> getAvailableSeats(@PathVariable Long bus_id) {
        return routeBusService.getAvailableSeats(bus_id);
    }

//    @PostMapping("/book")
//    public ResponseEntity<?> bookTicket(@RequestBody TicketBookingRequest bookingRequest) {
//        try {
//            Ticket bookedTicket = ticketService.bookTicket(bookingRequest);
//            return new ResponseEntity<Ticket>(bookedTicket, HttpStatus.CREATED);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }

    @PostMapping("/book")
    public ResponseEntity<ApiResponse<TicketDto>> bookTicket(@RequestBody TicketBookingRequest bookingRequest) {
        try {
            TicketDto bookedTicket = ticketService.bookTicket(bookingRequest);
            ApiResponse<TicketDto> response = new ApiResponse<>("success", "Ticket booked successfully", bookedTicket);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            ApiResponse<TicketDto> response = new ApiResponse<>("error", e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
