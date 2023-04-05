package io.skai.reservation.repository.impl;

import io.skai.reservation.jooq.Tables;
import io.skai.reservation.model.TicketModel;
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
    public TicketModel insert(TicketModel ticket) {
        return dslContext.insertInto(Tables.TICKET_)
                .set(Tables.TICKET_.PASSENGER_ID, ticket.passengerId())
                .set(Tables.TICKET_.PRICE, ticket.price())
                .set(Tables.TICKET_.FLIGHT_ID, ticket.flightId())
                .returning()
                .fetchOneInto(TicketModel.class);
    }

    @Override
    public List<TicketModel> selectAll() {
        return dslContext.selectFrom(Tables.TICKET_)
                .fetchInto(TicketModel.class);
    }
}
