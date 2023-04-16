package io.skai.reservation.repository;

import io.skai.reservation.jooq.tables.pojos.Passenger;

import java.util.List;

public interface PassengerRepository {

    Passenger insert(Passenger passenger);

    List<Passenger> selectPassengers();

    Passenger selectPassenger(Long id);
}