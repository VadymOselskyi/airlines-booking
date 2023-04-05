package io.skai.reservation.service;

import io.skai.reservation.model.TicketModel;

import java.util.List;

public interface TicketService {

    TicketModel create(TicketModel ticket);

    List<TicketModel> getAllTickets();
    List<TicketModel> getAllTicketsByPassengerEmail(String email);
}
