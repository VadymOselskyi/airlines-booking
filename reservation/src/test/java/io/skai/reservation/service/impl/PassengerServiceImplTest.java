package io.skai.reservation.service.impl;

import io.skai.reservation.dto.PassengerDto;
import io.skai.reservation.jooq.tables.pojos.Passenger;
import io.skai.reservation.mapper.PassengerMapper;
import io.skai.reservation.repository.PassengerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

class PassengerServiceImplTest {

    @InjectMocks
    PassengerServiceImpl passengerService;
    @Mock
    PassengerRepository passengerRepository;
    @Mock
    PassengerMapper passengerMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void WhenCreateOnePassengerInsertWillWorkOnceTest() {
        passengerService.create(any());

        verify(passengerRepository, times(1)).insert(any());
    }

    @Test
    void WhenCreateTwoPassengerInsertWillWorkTwiceTest() {
        passengerService.create(any());
        passengerService.create(any());

        verify(passengerRepository, times(2)).insert(any());
    }

    @Test
    void WhenGetByPassengerByEmailShouldReturnPassengerDtoTest() {
        Passenger passenger = new Passenger(1L, "Vadim", "Oselskyi", "vo@gmail.com", "+3806834232");
        PassengerDto passengerDto = new PassengerDto(1L, "Vadim", "Oselskyi", "vo@gmail.com", "+3806834232");

        when(passengerRepository.selectPassenger(anyString())).thenReturn(passenger);
        when(passengerMapper.passengerTOPassengerDto(any())).thenReturn(passengerDto);
        PassengerDto passengerByEmail = passengerService.getPassengerByEmail(anyString());

        assertThat(passengerByEmail.id(), equalTo(passenger.getId()));
        assertThat(passengerByEmail.firstName(), equalTo(passenger.getFirstName()));
        assertThat(passengerByEmail.lastName(), equalTo(passenger.getLastName()));
        assertThat(passengerByEmail.email(), equalTo(passenger.getEmail()));
        assertThat(passengerByEmail.phone(), equalTo(passenger.getPhone()));
    }
}