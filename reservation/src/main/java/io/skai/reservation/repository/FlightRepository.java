package io.skai.reservation.repository;

import io.skai.reservation.jooq.tables.pojos.Flight;

import java.util.List;

public interface FlightRepository {

    Flight insert(Flight flight);

    List<Flight> selectAll();

    Flight selectOneById(Long id);
}