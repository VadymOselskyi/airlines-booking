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
import io.skai.reservation.service.DbArchiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DbArchiveServiceImpl implements DbArchiveService {

    private final AirportRepository airportRepository;
    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;
    private final TicketRepository ticketRepository;
    private final HistoryTicketMapper mapper;

    @Override
    public List<HistoryTicketDto> getForArchive() {
        List<HistoryTicketDto> dtoList = new ArrayList<>();
        List<Ticket> tickets = ticketRepository.getTicketByFlightExpiredDateTime();

        for (Ticket ticket : tickets) {
            Flight flight = flightRepository.get(ticket.getFlightId());
            Airport arrivalAirport = airportRepository.selectOne(flight.getArrivalAirportId());
            Airport departureAirport = airportRepository.selectOne(flight.getDepartureAirportId());
            Passenger passenger = passengerRepository.findById(ticket.getPassengerId()).orElseThrow();
            dtoList.add(mapper.mapToDto(departureAirport, arrivalAirport, flight, passenger, ticket));
        }
        return dtoList;
    }
}