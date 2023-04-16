package io.skai.historicaldata.service;

import io.skai.historicaldata.dto.HistoricalTicketDto;

import java.util.List;

public interface TicketService {
    List<HistoricalTicketDto> getTickets(String email);

    HistoricalTicketDto saveTicket(HistoricalTicketDto ticketDto);
}