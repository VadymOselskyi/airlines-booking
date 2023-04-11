package io.skai.reservation.repository.impl;

import io.skai.reservation.jooq.Tables;
import io.skai.reservation.jooq.tables.pojos.Ticket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;

class TicketRepositoryImplTest extends BaseApplicationContextTest {

    @AfterEach
    void tearDown() {
        dslContext.truncate(Tables.TICKET)
                .execute();
    }

    @Test
    void whenNotingWasInsertedThenSelectAllReturnEmptyList() {
        List<Ticket> tickets = ticketRepository.selectAll();

        assertThat(tickets, empty());
    }

    @Test
    void whenInsertOneItemThenSelectAllShouldReturnListWithOneTicket() {
        Ticket ticket = new Ticket(1L, 1L, 1L, new BigDecimal(250));
        Ticket insertedTicket = ticketRepository.insert(ticket);
        List<Ticket> tickets = ticketRepository.selectAll();

        assertThat(tickets, contains(insertedTicket));
    }
}