package io.skai.reservation.service;

import io.skai.reservation.dto.AirportDto;

import java.util.List;

public interface AirportService {

    AirportDto create(AirportDto airport);

    List<AirportDto> getAllAirports();

    AirportDto getAirport(Long id);

}
