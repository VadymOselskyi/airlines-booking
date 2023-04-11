package io.skai.reservation.service.impl;


import io.skai.reservation.dto.FlightDto;
import io.skai.reservation.jooq.tables.pojos.Flight;
import io.skai.reservation.mapper.FlightMapper;
import io.skai.reservation.repository.FlightRepository;
import io.skai.reservation.service.FlightService;
import io.skai.reservation.validator.AirportValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final AirportValidator airportValidator;

    @Override
    public FlightDto create(FlightDto flightDto) {
        Flight flight = flightMapper.flightDtoToFlight(flightDto);
        airportValidator.validate(flight.getDepartureAirportId(), flight.getArrivalAirportId());
        Flight insertedFlight = flightRepository.insert(flight);
        return flightMapper.flightToFlightDto(insertedFlight);
    }

    @Override
    public List<FlightDto> getAllFlights() {
        return flightRepository.selectAll()
                .stream()
                .map(flightMapper::flightToFlightDto)
                .toList();
    }

}