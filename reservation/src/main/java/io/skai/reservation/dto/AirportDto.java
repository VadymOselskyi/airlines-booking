package io.skai.reservation.dto;

import java.io.Serializable;

public record AirportDto(Long id,
                         String name,
                         String countryCode,
                         String city) implements Serializable {
}