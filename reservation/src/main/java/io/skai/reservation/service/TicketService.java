package io.skai.reservation.service;

import io.skai.reservation.dto.TicketDto;

import java.util.List;

public interface TicketService {

    TicketDto create(TicketDto ticket);

    List<TicketDto> getAllTickets();
}