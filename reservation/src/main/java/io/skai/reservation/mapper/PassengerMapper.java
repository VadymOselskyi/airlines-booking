package io.skai.reservation.mapper;

import io.skai.reservation.dto.PassengerDto;
import io.skai.reservation.jooq.tables.pojos.Passenger;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PassengerMapper {

    Passenger passengerDtoTOPassenger(PassengerDto dto);

    PassengerDto passengerTOPassengerDto(Passenger passenger);
}
