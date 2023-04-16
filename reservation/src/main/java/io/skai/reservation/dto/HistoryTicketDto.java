package io.skai.reservation.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record HistoryTicketDto(Long id,
                               String firstName,
                               String lastName,
                               String email,
                               String phone,
                               String seatNumber,
                               BigDecimal price,
                               LocalDateTime departureDate,
                               String departureAirportName,
                               String departureCountryCode,
                               String departureCity,
                               LocalDateTime arrivalDate,
                               String arrivalAirportName,
                               String arrivalCountryCode,
                               String arrivalCity) {
}