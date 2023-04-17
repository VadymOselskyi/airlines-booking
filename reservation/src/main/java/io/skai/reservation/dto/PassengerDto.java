package io.skai.reservation.dto;

public record PassengerDto(Long id,
                           String firstName,
                           String lastName,
                           String email,
                           String phone) {
}