package io.skai.reservation.service;

import io.skai.reservation.dto.PassengerDto;

import java.util.List;

public interface PassengerService {
    PassengerDto create(PassengerDto passenger);

    List<PassengerDto> getPassengers();
}