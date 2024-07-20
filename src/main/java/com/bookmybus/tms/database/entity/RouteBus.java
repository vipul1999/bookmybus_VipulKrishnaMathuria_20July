package com.bookmybus.tms.database.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "route_bus")
@Data
public class RouteBus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @Column(name = "date")
    private Date date;

    // Store booked seats as a comma-separated string
    @ElementCollection
    @CollectionTable(name = "route_bus_seats", joinColumns = @JoinColumn(name = "route_bus_id"))
    @Column(name = "seat_number")
    private List<String> bookedSeats = new ArrayList<>();

}
