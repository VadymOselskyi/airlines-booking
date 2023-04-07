package io.skai.reservation.service.impl;

import io.skai.reservation.mapper.PassengerMapper;
import io.skai.reservation.repository.PassengerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    void WhenCreateOnePassengerInsertWillWorkOnce() {
        passengerService.create(any());

        verify(passengerRepository, times(1)).insert(any());
    }
    @Test
    void WhenCreateTwoPassengerInsertWillWorkTwice() {
        passengerService.create(any());
        passengerService.create(any());

        verify(passengerRepository, times(2)).insert(any());
    }

}