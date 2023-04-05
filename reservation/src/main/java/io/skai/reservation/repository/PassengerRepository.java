package io.skai.reservation.repository;

import io.skai.reservation.model.PassengerModel;

public interface PassengerRepository {
    PassengerModel insert (PassengerModel passenger);
    PassengerModel selectPassenger(String email);
    PassengerModel selectPassenger(Long id);
}
