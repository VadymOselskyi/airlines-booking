package io.skai.reservation.service.impl;

import io.skai.reservation.dto.FlightDto;
import io.skai.reservation.exception.EntityDoesNotExistsException;
import io.skai.reservation.jooq.tables.pojos.Flight;
import io.skai.reservation.mapper.FlightMapper;
import io.skai.reservation.repository.FlightRepository;
import io.skai.reservation.validator.AirportValidator;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class FlightServiceImplTest {

    private final FlightRepository flightRepository = mock(FlightRepository.class);
    private final FlightMapper flightMapper = mock(FlightMapper.class);
    private final AirportValidator airportValidator = mock(AirportValidator.class);
    private final FlightServiceImpl flightService = new FlightServiceImpl(flightRepository, flightMapper, airportValidator);

    @Test
    void whenDbHasOneElementGetAllFlightsShouldReturnOneItem() {
        Flight flight = new Flight(1L, LocalDateTime.of(2023, 4, 11, 8, 40),
                1L, LocalDateTime.of(2023, 4, 11, 8, 40), 2L);
        FlightDto flightDto = new FlightDto(1L, LocalDateTime.of(2023, 4, 11, 8, 40),
                LocalDateTime.of(2023, 4, 11, 8, 40), 1L, 2L);

        when(flightRepository.selectAll()).thenReturn(List.of(flight));
        when(flightMapper.flightToFlightDto(flight)).thenReturn(flightDto);

        List<FlightDto> allFlights = flightService.getAllFlights();

        assertThat(allFlights)
                .hasSize(1)
                .contains(flightDto);

        verify(flightRepository).selectAll();
        verify(flightMapper).flightToFlightDto(flight);
    }

    @Test
    void whenCreateOneFlightThenFlightRepositoryShouldInsertOnce() {
        Flight flight = new Flight(1L, LocalDateTime.of(2023, 4, 11, 8, 40),
                1L, LocalDateTime.of(2023, 4, 11, 8, 40), 2L);
        FlightDto flightDto = new FlightDto(1L, LocalDateTime.of(2023, 4, 11, 8, 40),
                LocalDateTime.of(2023, 4, 11, 8, 40), 1L, 2L);

        when(flightMapper.flightDtoToFlight(flightDto)).thenReturn(flight);

        flightService.create(flightDto);

        verify(flightRepository).insert(flight);
        verify(flightMapper).flightDtoToFlight(flightDto);
        verify(airportValidator).validate(flight.getDepartureAirportId(), flight.getArrivalAirportId());
    }

    @Test
    void whenAirportsWithIdsDoesNotExistsCreateShouldThrowException() {
        Flight flight = new Flight(1L, LocalDateTime.of(2023, 4, 11, 8, 40),
                1L, LocalDateTime.of(2023, 4, 11, 8, 40), 2L);
        FlightDto flightDto = new FlightDto(1L, LocalDateTime.of(2023, 4, 11, 8, 40),
                LocalDateTime.of(2023, 4, 11, 8, 40), 1L, 2L);

        when(flightMapper.flightDtoToFlight(flightDto)).thenReturn(flight);

        doThrow(new EntityDoesNotExistsException("Airports with ids:"
                + flightDto.departureAirportId() + " or " + flightDto.arrivalAirportId()
                + " are not exists"))
                .when(airportValidator).validate(flight.getDepartureAirportId(), flight.getArrivalAirportId());


        assertThatThrownBy(() -> flightService.create(flightDto))
                .isInstanceOf(EntityDoesNotExistsException.class)
                .hasMessage("Airports with ids:"
                        + flightDto.departureAirportId() + " or " + flightDto.arrivalAirportId()
                        + " are not exists");
    }
}