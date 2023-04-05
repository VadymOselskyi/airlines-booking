package io.skai.reservation.service;

import io.skai.reservation.model.FlightModel;

import java.util.List;

public interface FlightService {

    FlightModel create(FlightModel flight);

    List<FlightModel> getAllFlights();

    List<FlightModel> getAllFlightsByDepartureDate(String date);
}
