package com.bookmybus.tms.database.repository;

import com.bookmybus.tms.database.entity.RouteBus;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface RouteBusRepository extends CrudRepository<RouteBus, Long> {
    List<RouteBus> findByRouteIdAndDate(Long routeId, Date date);

}
