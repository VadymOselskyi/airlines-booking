package io.skai.reservation.repository;

import io.skai.reservation.jooq.tables.pojos.Airport;

import java.util.List;

public interface AirportRepository {

    Airport insert(String name, String countryCode, String city);

    List<Airport> selectAll();

    Airport selectOne(Long id);
}