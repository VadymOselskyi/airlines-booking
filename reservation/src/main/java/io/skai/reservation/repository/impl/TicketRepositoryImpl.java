package io.skai.reservation.repository.impl;

import io.skai.reservation.jooq.Tables;
import io.skai.reservation.jooq.tables.pojos.Ticket;
import io.skai.reservation.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TicketRepositoryImpl implements TicketRepository {

    private final DSLContext dslContext;

    @Override
    public Ticket insert(Ticket ticket) {
        return dslContext.insertInto(Tables.TICKET)
                .set(Tables.TICKET.PASSENGER_ID, ticket.getPassengerId())
                .set(Tables.TICKET.PRICE, ticket.getPrice())
                .set(Tables.TICKET.FLIGHT_ID, ticket.getFlightId())
                .returning()
                .fetchOneInto(Ticket.class);
    }

    @Override
    public List<Ticket> selectAll() {
        return dslContext.selectFrom(Tables.TICKET)
                .fetchInto(Ticket.class);
    }
}
