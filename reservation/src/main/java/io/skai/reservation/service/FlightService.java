package io.skai.reservation.service;

import io.skai.reservation.dto.FlightDto;

import java.util.List;

public interface FlightService {

    FlightDto create(FlightDto flight);

    List<FlightDto> getAllFlights();

    List<FlightDto> getAllFlightsByDepartureDate(String date);
}
