package com.bookmybus.tms.dtos;

import com.bookmybus.tms.database.entity.Bus;
import lombok.Data;

@Data
public class BusAvailability {
    private Bus bus;
    private int availableSeats;
}

