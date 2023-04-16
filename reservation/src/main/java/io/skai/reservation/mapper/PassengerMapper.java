package io.skai.reservation.mapper;

import io.skai.reservation.dto.PassengerDto;
import io.skai.reservation.jooq.tables.pojos.Passenger;
import io.skai.reservation.model.PassengerModel;
import org.mapstruct.Mapper;

@Mapper
public interface PassengerMapper {

    Passenger passengerDtoTOPassenger(PassengerDto dto);

    PassengerModel passengerDtoTOPassengerModel(PassengerDto dto);

    PassengerDto passengerToPassengerDto(Passenger passenger);

    PassengerDto passengerModelTOPassengerDto(PassengerModel passenger);
}