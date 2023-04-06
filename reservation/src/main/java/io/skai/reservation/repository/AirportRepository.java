package io.skai.reservation.repository;

import io.skai.reservation.jooq.tables.pojos.Airport;

import java.util.List;

public interface AirportRepository {

    Airport insert(Airport airport);

    List<Airport> selectAll();
    Airport selectOneById(Long id);
}
