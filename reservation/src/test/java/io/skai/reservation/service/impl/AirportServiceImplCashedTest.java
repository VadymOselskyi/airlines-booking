package io.skai.reservation.service.impl;

import io.skai.reservation.jooq.tables.pojos.Airport;
import io.skai.reservation.repository.AirportRepository;
import io.skai.reservation.service.AirportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class AirportServiceImplCashedTest {

    private final Airport LVIV_AIRPORT = new Airport(1L, "west", "UA", "Lviv");

    @MockBean
    private AirportRepository airportRepository;

    @Autowired
    private AirportService airportService;

    @Test
    void whenGetTwoAirportsThenSelectOneShouldVerifyOnce() {
        when(airportRepository.selectOne(LVIV_AIRPORT.getId())).thenReturn(LVIV_AIRPORT);

        airportService.getAirport(LVIV_AIRPORT.getId());
        airportService.getAirport(LVIV_AIRPORT.getId());

        verify(airportRepository)
                .selectOne(LVIV_AIRPORT.getId());
    }

    @Test
    void whenGetAllTwiceThenSelectAllWorkOnce() {
        when(airportRepository.selectAll()).thenReturn(List.of(LVIV_AIRPORT));

        airportService.getAllAirports();
        airportService.getAllAirports();

        verify(airportRepository)
                .selectAll();
    }
}