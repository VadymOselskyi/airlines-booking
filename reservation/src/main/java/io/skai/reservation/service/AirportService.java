package io.skai.reservation.service;

import io.skai.reservation.model.AirportModel;

import java.util.List;

public interface AirportService {

    AirportModel create(AirportModel airport);
    List<AirportModel> getAllAirports();
    AirportModel getAirport(Long id);

}
