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

    private static final Airport LVIV_AIRPORT = new Airport(1L, "Lviv International airport", "UA", "Lviv");
    private static final Airport ODESA_AIRPORT = new Airport(2L, "Odesa Main airport", "UA", "Odesa");
    private static final Flight FLIGHT = new Flight(1L, LocalDateTime.of(2023, 4, 11, 8, 40),
            1L, LocalDateTime.of(2023, 4, 11, 8, 40), 2L);
    private static final Passenger PASSENGER = new Passenger(1L, "Vadim", "Oselskyi", "vo@gmail.com", "+3806834232");
    private static final Ticket TICKET = new Ticket(1L, 1L, 1L, new BigDecimal(120), "1B");
    private static final HistoryTicketDto EXPECTED_DTO = new HistoryTicketDto(PASSENGER.getFirstName(), PASSENGER.getLastName(),
            PASSENGER.getEmail(), PASSENGER.getPhone(), TICKET.getSeatNumber(), TICKET.getPrice(),
            FLIGHT.getDepartureDate(), LVIV_AIRPORT.getName(), LVIV_AIRPORT.getCountryCode(), LVIV_AIRPORT.getCity(),
            FLIGHT.getArrivalDate(), ODESA_AIRPORT.getName(), ODESA_AIRPORT.getCountryCode(), ODESA_AIRPORT.getCity());

    private final HistoryTicketMapper ticketMapper = Mappers.getMapper(HistoryTicketMapper.class);

    @Test
    void whenInputRequiredEntitiesThenReturnHistoryTicketDto() {
        HistoryTicketDto actualDto = ticketMapper.mapToDto(LVIV_AIRPORT, ODESA_AIRPORT, FLIGHT, PASSENGER, TICKET);

        assertThat(actualDto).isEqualTo(EXPECTED_DTO);
    }
}