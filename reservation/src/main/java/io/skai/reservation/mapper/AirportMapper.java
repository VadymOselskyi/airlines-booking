package io.skai.reservation.mapper;

import io.skai.reservation.dto.AirportDto;
import io.skai.reservation.jooq.tables.pojos.Airport;
import org.mapstruct.Mapper;

@Mapper
public interface AirportMapper {

    AirportDto airportToAirportDto(Airport airport);
}