package com.bookmybus.tms.database.entity;

import com.bookmybus.tms.enums.TicketStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ticket")
@Data
public class Ticket extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "route_bus_id")
    @JsonIgnoreProperties
    private RouteBus routeBus;

    @Column(name = "seat_number")
    private int seatNumber;

    @OneToOne
    @JsonIgnoreProperties
    private Customer customer;

    @Column(name = "booking_time")
    private Date bookingTime;

    @Column(name = "ticket_status")
    private TicketStatus ticketStatus;

    @OneToOne
    @JsonIgnoreProperties
    private Route route;
}
