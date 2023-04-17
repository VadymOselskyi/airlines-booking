package io.skai.reservation.repository;


import io.skai.reservation.jooq.tables.pojos.Ticket;

import java.util.List;

public interface TicketRepository {
    Ticket insert(Ticket ticket);

    List<Ticket> selectAll();
}