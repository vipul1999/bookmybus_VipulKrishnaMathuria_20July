package com.bookmybus.tms.dtos;


import lombok.Data;

@Data
public class TicketBookingRequest {
    private Long routeBusId;
    private int seatNumber;
    private Long customerId;
}
