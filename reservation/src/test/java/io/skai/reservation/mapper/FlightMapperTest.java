package io.skai.reservation.mapper;

import io.skai.reservation.dto.FlightDto;
import io.skai.reservation.jooq.tables.pojos.Flight;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class FlightMapperTest {

    private final Flight FLIGHT = new Flight(1L, LocalDateTime.of(2023, 4, 11, 8, 40),
            1L, LocalDateTime.of(2023, 4, 11, 8, 40), 2L);
    private final FlightDto FLIGHTDTO = new FlightDto(1L, LocalDateTime.of(2023, 4, 11, 8, 40),
            LocalDateTime.of(2023, 4, 11, 8, 40), 1L, 2L);


    private final FlightMapper flightMapper = Mappers.getMapper(FlightMapper.class);

    @Test
    void whenInputFlightThenReturnFlightDto() {
        FlightDto actualDto = flightMapper.flightToFlightDto(FLIGHT);

        assertThat(actualDto).isEqualTo(FLIGHTDTO);
    }

    @Test
    void whenInputFlightDtoThenReturnFlight() {
        Flight actualFlight = flightMapper.flightDtoToFlight(FLIGHTDTO);

        assertThat(actualFlight).isEqualTo(FLIGHT);
    }
}