package io.skai.reservation.mapper;

import io.skai.reservation.dto.PassengerDto;
import io.skai.reservation.model.Passenger;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class PassengerMapperTest {

    private final Passenger PASSENGER = new Passenger(1L, "Vadim", "Oselskyi", "vo@gmail.com", "+3806834232");
    private final PassengerDto PASSENGERDTO = new PassengerDto(1L, "Vadim", "Oselskyi", "vo@gmail.com", "+3806834232");

    private final PassengerMapper passengerMapper = Mappers.getMapper(PassengerMapper.class);

    @Test
    void whenInputPassengerThenReturnPassengerDto() {
        PassengerDto actualDto = passengerMapper.passengerToPassengerDto(PASSENGER);

        assertThat(actualDto).isEqualTo(PASSENGERDTO);
    }

    @Test
    void whenInputPassengerDtoThenReturnPassenger() {
        Passenger actualPassenger = passengerMapper.passengerDtoToPassenger(PASSENGERDTO);

        assertThat(actualPassenger).isEqualTo(PASSENGER);
    }
}