package io.skai.reservation.mapper;

import io.skai.reservation.dto.FlightDto;
import io.skai.reservation.jooq.tables.pojos.Flight;
import org.mapstruct.Mapper;

@Mapper
public interface FlightMapper {
    Flight flightDtoToFlight(FlightDto dto);

    FlightDto flightToFlightDto(Flight flight);
}
