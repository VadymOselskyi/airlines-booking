package io.skai.historicaldata.service;

import io.skai.historicaldata.dto.HistoricalTicketDto;
import io.skai.historicaldata.mapper.TicketMapper;
import io.skai.historicaldata.model.HistoricalTicket;
import io.skai.historicaldata.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    @Override
    public List<HistoricalTicketDto> getTickets(String email) {

        return ticketRepository.find(email).stream()
                .map(ticketMapper::historicalTicketToHistoricalTicketDto)
                .toList();
    }

    @Override
    public HistoricalTicketDto saveTicket(HistoricalTicketDto ticketDto) {
        HistoricalTicket historicalTicket = ticketMapper.historicalTicketDtoToHistoricalTicket(ticketDto);
        return ticketMapper.historicalTicketToHistoricalTicketDto(ticketRepository.save(historicalTicket));
    }
}