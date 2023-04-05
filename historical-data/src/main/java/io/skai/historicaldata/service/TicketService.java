package io.skai.historicaldata.service;

import io.skai.historicaldata.model.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> getTicketByUserId(Long userId);

    Ticket saveTicket(Ticket ticket);
}
