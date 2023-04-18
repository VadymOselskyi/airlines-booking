package io.skai.reservation.mapper;

import io.skai.reservation.dto.AirportDto;
import io.skai.reservation.jooq.tables.pojos.Airport;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class AirportMapperTest {

    private final AirportMapper airportMapper = Mappers.getMapper(AirportMapper.class);

    @Test
    void whenInputAirportThenReturnRightAirportDto() {
        Airport airport = new Airport(1L, "International Main", "UA", "Kyiv");
        AirportDto expectedDto = new AirportDto(1L, "International Main", "UA", "Kyiv");

        AirportDto actualDto = airportMapper.airportToAirportDto(airport);

        assertThat(expectedDto).isEqualTo(actualDto);
    }
}