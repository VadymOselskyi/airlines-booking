package io.skai.reservation.service.impl;

import io.skai.reservation.model.PassengerModel;
import io.skai.reservation.repository.PassengerRepository;
import io.skai.reservation.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;

    @Override
    public PassengerModel create(PassengerModel passenger) {
        return passengerRepository.insert(passenger);
    }

    @Override
    public PassengerModel getPassengerByEmail(String email) {
        return passengerRepository.selectPassenger(email);
    }
}
