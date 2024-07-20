package com.bookmybus.tms.database.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "bus")
@Data
public class Bus extends AuditModel{
    //will the bus run only one route only, can the same bus not run on two routes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vehicle_registration_number")
    private String vehicleRegistrationNumber;

    @Column(name = "arrival_time")
    private Date arrivalTime;
    //reach time = arrival_Time + journey_Time of route_id
    @OneToOne
    private BusManager busManager;

    @Column(name = "capacity")
    private int capacity;//passenger capacity
    /*
    *
    *
    * */
}
