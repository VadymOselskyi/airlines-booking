package io.skai.reservation.service.impl;

import io.skai.reservation.dto.PassengerDto;
import io.skai.reservation.jooq.tables.pojos.Passenger;
import io.skai.reservation.mapper.PassengerMapper;
import io.skai.reservation.repository.PassengerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PassengerServiceImplTest {

    PassengerRepository passengerRepository = Mockito.mock(PassengerRepository.class);
    PassengerMapper passengerMapper = Mockito.mock(PassengerMapper.class);

    PassengerServiceImpl passengerService = new PassengerServiceImpl(passengerRepository, passengerMapper);

    @Test
    void whenCreateTwoPassengerInsertWillWorkTwiceTest() {
        Passenger passenger = new Passenger(1L, "Vadim", "Oselskyi", "vo@gmail.com", "+3806834232");
        Passenger passenger2 = new Passenger(2L, "Anton", "Pascal", "an.pas@gmail.com", "+38006349529");
        PassengerDto passengerDto = new PassengerDto(1L, "Vadim", "Oselskyi", "vo@gmail.com", "+3806834232");
        PassengerDto passengerDto2 = new PassengerDto(2L, "Anton", "Pascal", "an.pas@gmail.com", "+38006349529");

        when(passengerMapper.passengerDtoTOPassenger(passengerDto)).thenReturn(passenger);
        when(passengerMapper.passengerDtoTOPassenger(passengerDto2)).thenReturn(passenger2);

        passengerService.create(passengerDto);
        passengerService.create(passengerDto2);

        verify(passengerRepository).insert(passenger);
        verify(passengerRepository).insert(passenger2);
    }

    @Test
    void whenGetByPassengerByEmailShouldReturnPassengerDtoTest() {
        Passenger passenger = new Passenger(1L, "Vadim", "Oselskyi", "vo@gmail.com", "+3806834232");
        PassengerDto passengerDto = new PassengerDto(1L, "Vadim", "Oselskyi", "vo@gmail.com", "+3806834232");

        when(passengerRepository.selectPassengers()).thenReturn(List.of(passenger));
        when(passengerMapper.passengerToPassengerDto(passenger)).thenReturn(passengerDto);
        List<PassengerDto> passengers = passengerService.getPassengers();

        assertThat(passengers)
                .hasSize(1)
                .contains(passengerDto);
    }
}