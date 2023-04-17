package io.skai.reservation.service.impl;

import io.skai.reservation.dto.TicketDto;
import io.skai.reservation.jooq.tables.pojos.Ticket;
import io.skai.reservation.mapper.TicketMapper;
import io.skai.reservation.repository.TicketRepository;
import io.skai.reservation.service.TicketService;
import io.skai.reservation.validator.FlightValidator;
import io.skai.reservation.validator.PassengerValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final FlightValidator flightValidator;
    private final PassengerValidator passengerValidator;

    @Override
    public TicketDto create(TicketDto ticketDto) {
        Ticket ticket = ticketMapper.ticketDtoToTicket(ticketDto);
        flightValidator.validate(ticket.getFlightId());
        passengerValidator.validate(ticket.getPassengerId());
        Ticket insertedTicket = ticketRepository.insert(ticket);
        return ticketMapper.ticketToTicketDto(insertedTicket);
    }

    @Override
    public List<TicketDto> getAllTickets() {
        return ticketRepository.selectAll()
                .stream()
                .map(ticketMapper::ticketToTicketDto)
                .toList();
    }
}