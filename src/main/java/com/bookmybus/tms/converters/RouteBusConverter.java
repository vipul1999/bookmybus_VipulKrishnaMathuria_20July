package com.bookmybus.tms.converters;


import com.bookmybus.tms.database.entity.RouteBus;
import com.bookmybus.tms.dtos.RouteBusDTO;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class RouteBusConverter {

    public static RouteBusDTO convertToDTO(RouteBus routeBus) {
        if (routeBus == null) {
            return null;
        }

        RouteBusDTO dto = new RouteBusDTO();
        dto.setId(routeBus.getId());
        dto.setRouteId(routeBus.getRoute() != null ? routeBus.getRoute().getId() : null);
        dto.setBusNumber(routeBus.getBus().getVehicleRegistrationNumber());

        // Convert arrivalTime to LocalDateTime and then to ZonedDateTime in IST
        ZonedDateTime arrivalTime = convertToZonedDateTime(routeBus.getBus().getArrivalTime(), ZoneId.of("Asia/Kolkata"));

        // Convert journeyTime to Duration
        Duration journeyDuration = convertToDuration(routeBus.getRoute().getJourneyTime());

        // Add journeyDuration to arrivalTime
        ZonedDateTime busDestinationTime = arrivalTime.plus(journeyDuration);

        // Convert ZonedDateTime back to LocalDateTime if needed for DTO
        LocalDateTime arrivalLocalDateTime = arrivalTime.toLocalDateTime();
        LocalDateTime destinationLocalDateTime = busDestinationTime.toLocalDateTime();

        dto.setBusArrivalTime(arrivalLocalDateTime);
        dto.setBusDestinationTime(destinationLocalDateTime);
        dto.setBookedSeats(routeBus.getBookedSeats());

        return dto;
    }


    private static ZonedDateTime convertToZonedDateTime(Date date, ZoneId zoneId) {
        return date.toInstant()
                .atZone(zoneId);
    }

    private static Duration convertToDuration(Date date) {
        // Assuming journeyTime is stored as a Date that represents a time duration
        // This method needs to be adjusted based on the actual representation of duration
        ZonedDateTime journeyTime = convertToZonedDateTime(date, ZoneId.of("Asia/Kolkata"));
        return Duration.ofHours(journeyTime.getHour())
                .plusMinutes(journeyTime.getMinute());
    }

    private static LocalDateTime convertToLocalDateTime(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }


    private static Date convertToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static List<RouteBusDTO> convertToDTOList(List<RouteBus> routeBusList) {
        return routeBusList.stream()
                .map(RouteBusConverter::convertToDTO)
                .collect(Collectors.toList());
    }
}
