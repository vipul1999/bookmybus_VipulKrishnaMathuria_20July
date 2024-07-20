package com.bookmybus.tms.database.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "bus_manager")
@Data
public class BusManager extends User{

}
