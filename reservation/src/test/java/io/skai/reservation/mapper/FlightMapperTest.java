package io.skai.reservation.mapper;

import io.skai.reservation.dto.FlightDto;
import io.skai.reservation.jooq.tables.pojos.Flight;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class FlightMapperTest {

    private final Flight flight = new Flight(1L, LocalDateTime.of(2023, 4, 11, 8, 40),
            1L, LocalDateTime.of(2023, 4, 11, 8, 40), 2L);
    private final FlightDto flightDto = new FlightDto(1L, LocalDateTime.of(2023, 4, 11, 8, 40),
            LocalDateTime.of(2023, 4, 11, 8, 40), 1L, 2L);

    private final FlightMapper flightMapper = Mappers.getMapper(FlightMapper.class);

    @Test
    void whenInputFlightThenReturnFlightDto() {
        FlightDto actualDto = flightMapper.flightToFlightDto(flight);

        assertThat(actualDto).isEqualTo(flightDto);
    }

    @Test
    void whenInputFlightDtoThenReturnFlight() {
        Flight actualFlight = flightMapper.flightDtoToFlight(flightDto);

        assertThat(actualFlight).isEqualTo(flight);
    }
}