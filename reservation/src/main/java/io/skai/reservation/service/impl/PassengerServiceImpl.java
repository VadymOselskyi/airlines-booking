package io.skai.reservation.service.impl;

import io.skai.reservation.dto.PassengerDto;
import io.skai.reservation.jooq.tables.pojos.Passenger;
import io.skai.reservation.mapper.PassengerMapper;
import io.skai.reservation.repository.PassengerRepository;
import io.skai.reservation.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    private final PassengerMapper passengerMapper;

    @Override
    public PassengerDto create(PassengerDto passengerDto) {
        Passenger passenger = passengerMapper.passengerDtoTOPassenger(passengerDto);
        Passenger insertedPassenger = passengerRepository.insert(passenger);
        return passengerMapper.passengerToPassengerDto(insertedPassenger);
    }

    @Override
    public List<PassengerDto> getPassengers() {
        List<Passenger> passengers = passengerRepository.selectPassengers();
        return passengers.stream()
                .map(passengerMapper::passengerToPassengerDto)
                .toList();
    }
}