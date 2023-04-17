package io.skai.reservation.mapper;

import io.skai.reservation.dto.PassengerDto;
import io.skai.reservation.model.Passenger;
import org.mapstruct.Mapper;

@Mapper
public interface PassengerMapper {

    Passenger passengerDtoToPassenger(PassengerDto dto);

    PassengerDto passengerToPassengerDto(Passenger passenger);

}