package io.skai.reservation.repository.impl;

import io.skai.reservation.jooq.tables.pojos.Ticket;
import io.skai.reservation.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static io.skai.reservation.jooq.Tables.FLIGHT;
import static io.skai.reservation.jooq.Tables.TICKET;

@Repository
@RequiredArgsConstructor
public class TicketRepositoryImpl implements TicketRepository {

    private final DSLContext dslContext;

    @Override
    public Ticket insert(Ticket ticket) {
        return dslContext.insertInto(TICKET)
                .set(TICKET.PASSENGER_ID, ticket.getPassengerId())
                .set(TICKET.PRICE, ticket.getPrice())
                .set(TICKET.FLIGHT_ID, ticket.getFlightId())
                .returning()
                .fetchOneInto(Ticket.class);
    }

    @Override
    public List<Ticket> selectAll() {
        return dslContext.selectFrom(TICKET)
                .fetchInto(Ticket.class);
    }

    @Override
    public List<Ticket> getTicketByFlightExpiredDateTime() {
        return dslContext.select()
                .from(TICKET.join(FLIGHT).on(TICKET.FLIGHT_ID.eq(FLIGHT.ID)))
                .where(FLIGHT.DEPARTURE_DATE.lessThan(LocalDateTime.now()))
                .fetchInto(Ticket.class);
    }
}