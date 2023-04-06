package io.skai.reservation.service;

import io.skai.reservation.dto.PassengerDto;

public interface PassengerService {
    PassengerDto create(PassengerDto passenger);
    PassengerDto getPassengerByEmail(String email);
}
