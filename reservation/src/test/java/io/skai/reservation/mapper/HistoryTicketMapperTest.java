package io.skai.reservation.mapper;

import io.skai.reservation.dto.HistoryTicketDto;
import io.skai.reservation.jooq.tables.pojos.Airport;
import io.skai.reservation.jooq.tables.pojos.Flight;
import io.skai.reservation.jooq.tables.pojos.Ticket;
import io.skai.reservation.model.Passenger;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class HistoryTicketMapperTest {

    private final HistoryTicketMapper ticketMapper = Mappers.getMapper(HistoryTicketMapper.class);

    @Test
    void whenInputRequiredEntitiesThenReturnHistoryTicketDto() {
        Airport LVIV_AIRPORT = new Airport(1L, "west", "UA", "Lviv");
        Airport ODESA_AIRPORT = new Airport(2L, "south", "UA", "Odesa");
        Flight flight = new Flight(1L, LocalDateTime.of(2023, 4, 11, 8, 40),
                1L, LocalDateTime.of(2023, 4, 11, 8, 40), 2L);
        Passenger passenger = new Passenger(1L, "Vadim", "Oselskyi", "vo@gmail.com", "+3806834232");
        Ticket ticket = new Ticket(1L, 1L, 1L, new BigDecimal(120), "1B");
        HistoryTicketDto expectedDto = new HistoryTicketDto(passenger.getFirstName(), passenger.getLastName(),
                passenger.getEmail(), passenger.getPhone(), ticket.getSeatNumber(), ticket.getPrice(),
                flight.getDepartureDate(), LVIV_AIRPORT.getName(), LVIV_AIRPORT.getCountryCode(), LVIV_AIRPORT.getCity(),
                flight.getArrivalDate(), ODESA_AIRPORT.getName(), ODESA_AIRPORT.getCountryCode(), ODESA_AIRPORT.getCity());

        HistoryTicketDto actualDto = ticketMapper.mapToDto(LVIV_AIRPORT, ODESA_AIRPORT, flight, passenger, ticket);

        assertThat(actualDto).isEqualTo(expectedDto);
    }
}