package io.skai.reservation.dto;

import java.time.LocalDateTime;

public record FlightDto(Long id,
                        LocalDateTime departureDate,
                        LocalDateTime arrivalDate,
                        Long departureAirportId,
                        Long arrivalAirportId) {
}