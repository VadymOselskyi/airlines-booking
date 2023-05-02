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
import io.skai.reservation.service.HistoryTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryTicketServiceImpl implements HistoryTicketService {

    private final AirportRepository airportRepository;
    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;
    private final TicketRepository ticketRepository;
    private final HistoryTicketMapper mapper;

    @Override
    public List<HistoryTicketDto> prepareHistory() {
        return ticketRepository.getTicketByFlightExpiredDateTime().stream()
                .map(this::createHistory)
                .toList();
    }

    private HistoryTicketDto createHistory(Ticket ticket) {
        Flight flight = flightRepository.get(ticket.getFlightId());
        Airport arrivalAirport = airportRepository.selectOne(flight.getArrivalAirportId());
        Airport departureAirport = airportRepository.selectOne(flight.getDepartureAirportId());
        Passenger passenger = passengerRepository.findById(ticket.getPassengerId()).orElseThrow();
        return mapper.mapToDto(departureAirport, arrivalAirport, flight, passenger, ticket);
    }
}