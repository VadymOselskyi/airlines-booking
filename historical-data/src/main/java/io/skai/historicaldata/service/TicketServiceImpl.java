package io.skai.historicaldata.service;

import io.skai.historicaldata.dto.HistoricalTicketDto;
import io.skai.historicaldata.exception.TicketWasNotSavedException;
import io.skai.historicaldata.mapper.TicketMapper;
import io.skai.historicaldata.model.HistoricalTicket;
import io.skai.historicaldata.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    @Override
    public List<HistoricalTicketDto> getTickets(String email) {
        return ticketRepository.findAll(email).stream()
                .map(ticketMapper::historicalTicketToHistoricalTicketDto)
                .toList();
    }

    @Override
    public HistoricalTicketDto saveTicket(HistoricalTicketDto ticketDto) {
        HistoricalTicket historicalTicket = ticketMapper.historicalTicketDtoToHistoricalTicket(ticketDto);
        HistoricalTicket ticket;
        try {
            ticket = ticketRepository.save(historicalTicket);
        } catch (IllegalArgumentException | OptimisticLockingFailureException exception){
            throw new TicketWasNotSavedException("Ticket wasn't saved into history database");
        }
        return ticketMapper.historicalTicketToHistoricalTicketDto(ticket);
    }
}