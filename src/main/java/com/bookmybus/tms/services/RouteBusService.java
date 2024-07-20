package com.bookmybus.tms.services;

import com.bookmybus.tms.database.entity.Route;
import com.bookmybus.tms.database.entity.RouteBus;
import com.bookmybus.tms.database.repository.RouteBusRepository;
import com.bookmybus.tms.database.repository.RouteRepository;
import com.bookmybus.tms.dtos.SeatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RouteBusService {
    @Autowired
    private RouteBusRepository routeBusRepository;
    @Autowired
    private RouteRepository routeRepository;

    public List<RouteBus> getBusesByRouteAndDate(Long routeId, Date date) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new RuntimeException("Route not found"));
        return routeBusRepository.findByRouteIdAndDate(routeId, date);
    }


    public List<SeatDTO> getAvailableSeats(Long routeBusId) {
        Optional<RouteBus> routeBusOptional = routeBusRepository.findById(routeBusId);

        if (routeBusOptional.isPresent()) {
            RouteBus routeBus = routeBusOptional.get();
            List<String> bookedSeats = routeBus.getBookedSeats();

            // Generate all possible seats
            List<String> allSeats = new
                    ArrayList<>(generateAllSeats());

            // Create a list to store seat availability
            List<SeatDTO> seatDTOs = new ArrayList<>();

            // Map each seat to its availability status
            for (String seat : allSeats) {
                boolean isAvailable = !bookedSeats.contains(seat);
                seatDTOs.add(new SeatDTO(seat, isAvailable));
            }

            return seatDTOs;
        } else {
            throw new RuntimeException("RouteBus with ID " + routeBusId + " not found.");
        }
    }

    private List<String> generateAllSeats() {
        // Generate a list of all possible seats based on your seat configuration
        // For example, 'A1', 'A2', ..., 'D10'
        // This is a placeholder; adjust based on actual seat configuration
        return List.of("A1", "A2", "A3", "A4", "B1", "B2", "B3", "B4", "C1", "C2", "C3", "C4", "D1", "D2", "D3", "D4");
    }





}
