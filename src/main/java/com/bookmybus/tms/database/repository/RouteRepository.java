package com.bookmybus.tms.database.repository;

import com.bookmybus.tms.database.entity.Route;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RouteRepository extends CrudRepository<Route, Long> {
    Optional<Route> findBySourceAndDestination(String source, String destination);

    List<Route> findAll();
}
