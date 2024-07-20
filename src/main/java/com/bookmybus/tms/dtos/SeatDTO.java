package com.bookmybus.tms.dtos;


import lombok.Data;

@Data
public class SeatDTO {
    private String seatNumber;
    private boolean isAvailable;

    // Constructors, Getters, and Setters
    public SeatDTO(String seatNumber, boolean isAvailable) {
        this.seatNumber = seatNumber;
        this.isAvailable = isAvailable;

    }
}
