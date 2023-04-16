package io.skai.reservation.service.impl;

import io.skai.reservation.dto.AirportDto;
import io.skai.reservation.jooq.tables.pojos.Airport;
import io.skai.reservation.mapper.AirportMapper;
import io.skai.reservation.repository.AirportRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AirportServiceImplTest {

    private final Airport LVIV_AIRPORT = new Airport(1L, "west", "UA", "Lviv");
    private final Airport ODESA_AIRPORT = new Airport(2L, "south", "UA", "Odesa");
    private final AirportDto LVIV_AIRPORTDTO = new AirportDto(1L, "west", "UA", "Lviv");
    private final AirportDto ODESA_AIRPORTDTO = new AirportDto(2L, "south", "UA", "Odesa");

    private final AirportRepository airportRepository = mock(AirportRepository.class);
    private final AirportMapper airportMapper = mock(AirportMapper.class);

    private final AirportServiceImpl airportService = new AirportServiceImpl(airportRepository, airportMapper);

    @Test
    void whenAddTwoAirportsGetAllAirportsShouldReturnTwoAirports() {

        List<Airport> airports = List.of(LVIV_AIRPORT, ODESA_AIRPORT);

        when(airportRepository.selectAll()).thenReturn(airports);
        List<AirportDto> airportsDto = airportService.getAllAirports();

        assertThat(airportsDto)
                .hasSize(2);

        verify(airportRepository).selectAll();
    }

    @Test
    void whenAddTwoAirportsGetAllAirportsShouldContains() {

        List<Airport> airports = List.of(LVIV_AIRPORT, ODESA_AIRPORT);

        when(airportRepository.selectAll()).thenReturn(airports);
        when(airportMapper.airportToAirportDto(LVIV_AIRPORT)).thenReturn(LVIV_AIRPORTDTO);
        when(airportMapper.airportToAirportDto(ODESA_AIRPORT)).thenReturn(ODESA_AIRPORTDTO);
        List<AirportDto> airportsDto = airportService.getAllAirports();

        assertThat(airportsDto)
                .extracting(AirportDto::name)
                .contains(LVIV_AIRPORT.getName(), ODESA_AIRPORT.getName());

        verify(airportRepository).selectAll();
    }

    @Test
    void whenCreateOneAirportInsertWillWriteOnce() {

        airportService.create(LVIV_AIRPORTDTO);

        verify(airportRepository)
                .insert(LVIV_AIRPORT.getName(), LVIV_AIRPORT.getCountryCode(), LVIV_AIRPORT.getCity());
    }

    @Test
    void whenCreateTwoAirportsInsertWillWorkTwice() {

        airportService.create(LVIV_AIRPORTDTO);
        airportService.create(ODESA_AIRPORTDTO);

        verify(airportRepository)
                .insert(LVIV_AIRPORT.getName(), LVIV_AIRPORT.getCountryCode(), LVIV_AIRPORT.getCity());

        verify(airportRepository)
                .insert(ODESA_AIRPORT.getName(), ODESA_AIRPORT.getCountryCode(), ODESA_AIRPORT.getCity());
    }
}