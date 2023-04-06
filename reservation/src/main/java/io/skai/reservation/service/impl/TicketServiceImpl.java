package io.skai.reservation.service.impl;

import io.skai.reservation.dto.TicketDto;
import io.skai.reservation.jooq.tables.pojos.Ticket;
import io.skai.reservation.mapper.TicketMapper;
import io.skai.reservation.repository.TicketRepository;
import io.skai.reservation.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    @Override
    public TicketDto create(TicketDto ticketDto) {
        Ticket ticket = ticketMapper.ticketDtoToTicket(ticketDto);
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

    @Override
    public List<TicketDto> getAllTicketsByPassengerEmail(String email) {
        return null;
    }
}
