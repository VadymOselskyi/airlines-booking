package io.skai.reservation.repository;

import io.skai.reservation.jooq.tables.pojos.Passenger;

public interface PassengerRepository {
    Passenger insert (Passenger passenger);
    Passenger selectPassenger(String email);
    Passenger selectPassenger(Long id);
}
