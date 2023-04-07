package io.skai.reservation.service.impl;

import io.skai.reservation.dto.AirportDto;
import io.skai.reservation.jooq.tables.pojos.Airport;
import io.skai.reservation.mapper.AirportMapper;
import io.skai.reservation.repository.AirportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AirportServiceImplTest {

    @InjectMocks
    AirportServiceImpl airportService;
    @Mock
    AirportRepository airportRepository;
    @Mock
    AirportMapper airportMapper;
    Airport west;
    Airport south;
    AirportDto westDto;
    AirportDto southDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        west = new Airport(1L, "west", "UA", "Lviv");
        south = new Airport(2L, "south", "UA", "Odesa");

        westDto = new AirportDto(1L, "west", "UA", "Lviv");
        southDto = new AirportDto(2L, "south", "UA", "Odesa");

    }


    @Test
    void whenAddTwoAirportsGetAllAirportsShouldReturnTwoAirportsTest() {

        List<Airport> airports = List.of(west, south);

        when(airportRepository.selectAll()).thenReturn(airports);
        List<AirportDto> airportsDto = airportService.getAllAirports();

        assertThat(airportsDto)
                .hasSize(2);

        verify(airportRepository, times(1)).selectAll();
    }

    @Test
    void whenAddTwoAirportsGetAllAirportsShouldContainsTest() {

        List<Airport> airports = List.of(west, south);

        when(airportRepository.selectAll()).thenReturn(airports);
        when(airportMapper.airportToAirportDto(west)).thenReturn(westDto);
        when(airportMapper.airportToAirportDto(south)).thenReturn(southDto);
        List<AirportDto> airportsDto = airportService.getAllAirports();

        assertThat(airportsDto)
                .extracting(AirportDto::name)
                .contains(west.getName(), south.getName());

        verify(airportRepository, times(1)).selectAll();
    }

    @Test
    void WhenCreateOneAirportInsertWillWriteOnceTest() {

        airportService.create(westDto);

        verify(airportRepository, times(1)).insert(any());
    }

    @Test
    void WhenCreateTwoAirportsInsertWillWorkTwiceTest() {

        airportService.create(westDto);
        airportService.create(southDto);

        verify(airportRepository, times(2)).insert(any());
    }

}