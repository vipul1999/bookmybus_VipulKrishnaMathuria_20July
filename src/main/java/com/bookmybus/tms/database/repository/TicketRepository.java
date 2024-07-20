package com.bookmybus.tms.database.repository;

import com.bookmybus.tms.database.entity.RouteBus;
import com.bookmybus.tms.database.entity.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket,Long> {
//    List<Ticket> findByRouteAndStatus(Route route, String status);

    boolean existsByRouteBusAndSeatNumber(RouteBus routeBus, int seatNumber);


}
