package io.skai.reservation.service.impl;

import io.skai.reservation.dto.HistoryTicketDto;
import io.skai.reservation.jooq.tables.pojos.Airport;
import io.skai.reservation.jooq.tables.pojos.Flight;
import io.skai.reservation.jooq.tables.pojos.Ticket;
import io.skai.reservation.mapper.HistoryTicketMapper;
import io.skai.reservation.model.Passenger;
import io.skai.reservation.repository.AirportRepository;
import io.skai.reservation.repository.FlightRepository;
import io.skai.reservation.repository.PassengerRepository;
import io.skai.reservation.repository.TicketRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DbArchiveServiceImplTest {

    private static final Airport LVIV_AIRPORT = getLvivAirport();
    private static final Airport ODESA_AIRPORT = getOdesaAirport();
    private static final Flight FLIGHT = getFlight();
    private static final Passenger PASSENGER = getPassenger();
    private static final Ticket TICKET = getTicket();
    private static final HistoryTicketDto HISTORY_TICKET_DTO = getHistoryTicketDto();

    private final AirportRepository airportRepository = mock(AirportRepository.class);
    private final FlightRepository flightRepository = mock(FlightRepository.class);
    private final PassengerRepository passengerRepository = mock(PassengerRepository.class);
    private final TicketRepository ticketRepository = mock(TicketRepository.class);
    private final HistoryTicketMapper mapper = mock(HistoryTicketMapper.class);
    private final DbArchiveServiceImpl dbArchiveService = new DbArchiveServiceImpl(airportRepository, flightRepository,
            passengerRepository, ticketRepository, mapper);

    @Test
    void whenDbHasExpiredTicketsThenReturnHistoryAirportDtoList() {
        when(airportRepository.selectOne(FLIGHT.getDepartureAirportId())).thenReturn(LVIV_AIRPORT);
        when(airportRepository.selectOne(FLIGHT.getArrivalAirportId())).thenReturn(ODESA_AIRPORT);
        when(flightRepository.get(TICKET.getFlightId())).thenReturn(FLIGHT);
        when(passengerRepository.findById(TICKET.getPassengerId())).thenReturn(Optional.of(PASSENGER));
        when(ticketRepository.getTicketByFlightExpiredDateTime()).thenReturn(List.of(TICKET));
        when(mapper.mapToDto(LVIV_AIRPORT, ODESA_AIRPORT, FLIGHT, PASSENGER, TICKET)).thenReturn(HISTORY_TICKET_DTO);

        List<HistoryTicketDto> historyTicketDtoList = dbArchiveService.getForArchive();

        assertThat(historyTicketDtoList).containsExactly(HISTORY_TICKET_DTO);

        verify(airportRepository).selectOne(LVIV_AIRPORT.getId());
        verify(airportRepository).selectOne(ODESA_AIRPORT.getId());
        verify(flightRepository).get(FLIGHT.getId());
        verify(passengerRepository).findById(PASSENGER.getId());
        verify(ticketRepository).getTicketByFlightExpiredDateTime();
        verify(mapper).mapToDto(LVIV_AIRPORT, ODESA_AIRPORT, FLIGHT, PASSENGER, TICKET);
    }

    private static Airport getLvivAirport() {
        return new Airport(1L, "Lviv International airport", "UA", "Lviv");
    }

    private static Airport getOdesaAirport() {
        return new Airport(2L, "Odesa Main airport", "UA", "Odesa");
    }

    private static Flight getFlight() {
        return new Flight(1L, LocalDateTime.of(2023, 4, 11, 8, 40), 1L,
                LocalDateTime.of(2023, 4, 11, 8, 40), 2L);
    }

    private static Passenger getPassenger() {
        return new Passenger(1L, "Vadim", "Oselskyi", "vo@gmail.com", "+3806834232");
    }

    private static Ticket getTicket() {
        return new Ticket(1L, 1L, 1L, new BigDecimal(120), "1B");
    }

    private static HistoryTicketDto getHistoryTicketDto() {
        return new HistoryTicketDto(PASSENGER.getFirstName(), PASSENGER.getLastName(),
                PASSENGER.getEmail(), PASSENGER.getPhone(), TICKET.getSeatNumber(), TICKET.getPrice(),
                FLIGHT.getDepartureDate(), LVIV_AIRPORT.getName(), LVIV_AIRPORT.getCountryCode(), LVIV_AIRPORT.getCity(),
                FLIGHT.getArrivalDate(), ODESA_AIRPORT.getName(), ODESA_AIRPORT.getCountryCode(), ODESA_AIRPORT.getCity());
    }
}