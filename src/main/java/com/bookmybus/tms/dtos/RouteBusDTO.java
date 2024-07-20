package com.bookmybus.tms.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class RouteBusDTO {

    private Long id;
    private Long routeId; // To reference the associated route
    private String busNumber; // To reference the associated bus
    private Date date;
    private List<String> bookedSeats;
    private LocalDateTime busArrivalTime;
    private LocalDateTime busDestinationTime;
    private LocalDateTime journeyTime;
}

