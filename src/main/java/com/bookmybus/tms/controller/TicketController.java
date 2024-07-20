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

    @GetMapping("/user/{user_id}/tickets")
    public ResponseEntity<ApiResponse<List<TicketDto>>> getUserTickets(@PathVariable Long user_id) {
        try {
            List<TicketDto> tickets = ticketService.getTicketsByUserId(user_id);
            ApiResponse<List<TicketDto>> response = new ApiResponse<>("success", "Tickets retrieved successfully", tickets);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            ApiResponse<List<TicketDto>> response = new ApiResponse<>("error", e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
