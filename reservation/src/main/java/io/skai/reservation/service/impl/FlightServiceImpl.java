package io.skai.reservation.service.impl;


import io.skai.reservation.model.FlightModel;
import io.skai.reservation.repository.FlightRepository;
import io.skai.reservation.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;

    @Override
    public FlightModel create(FlightModel flight) {
        return flightRepository.insert(flight);
    }

    @Override
    public List<FlightModel> getAllFlights() {
        return flightRepository.selectAll();
    }

    @Override
    public List<FlightModel> getAllFlightsByDepartureDate(String date) {
        return null;
    }
}
