package io.skai.reservation.dto;

public record AirportDto(
        Long id,
        String name,
        String countryCode,
        String city
) {
}
