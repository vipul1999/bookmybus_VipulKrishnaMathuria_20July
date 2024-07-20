package com.bookmybus.tms.services;

import com.bookmybus.tms.database.entity.Route;
import com.bookmybus.tms.database.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {
    @Autowired
    private RouteRepository routeRepository;

    public Route getRouteById(Long routeId) {
        return routeRepository.findById(routeId)
                .orElseThrow(() -> new RuntimeException("Route not found with id: " + routeId));
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Route getRouteBySourceAndDestination(String source, String destination) {
        // Assuming a method is implemented in the repository to find by source and destination
        return routeRepository.findBySourceAndDestination(source, destination)
                .orElseThrow(() -> new RuntimeException("Route not found from " + source + " to " + destination));
    }

}
