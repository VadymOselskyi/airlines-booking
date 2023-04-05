package io.skai.reservation.service;

import io.skai.reservation.model.PassengerModel;

public interface PassengerService {
    PassengerModel create(PassengerModel passenger);
    PassengerModel getPassengerByEmail(String email);
}
