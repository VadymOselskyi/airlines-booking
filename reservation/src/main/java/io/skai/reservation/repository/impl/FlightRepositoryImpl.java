package io.skai.reservation.repository.impl;

import io.skai.reservation.jooq.Tables;
import io.skai.reservation.jooq.tables.pojos.Flight;
import io.skai.reservation.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FlightRepositoryImpl implements FlightRepository {

    private final DSLContext dslContext;

    @Override
    public Flight insert(Flight flight) {
        return dslContext.insertInto(Tables.FLIGHT)
                .set(Tables.FLIGHT.ARRIVAL_AIRPORT_ID, flight.getArrivalAirportId())
                .set(Tables.FLIGHT.DEPARTURE_AIRPORT_ID, flight.getDepartureAirportId())
                .set(Tables.FLIGHT.ARRIVAL_DATE, flight.getArrivalDate())
                .set(Tables.FLIGHT.DEPARTURE_DATE, flight.getDepartureDate())
                .returning()
                .fetchOneInto(Flight.class);
    }

    @Override
    public List<Flight> selectAll() {
        return dslContext.selectFrom(Tables.FLIGHT)
                .fetchInto(Flight.class);
    }

    @Override
    public Flight selectOneById(Long id) {
        return dslContext.selectFrom(Tables.FLIGHT)
                .where(Tables.FLIGHT.ID.eq(id))
                .fetchOneInto(Flight.class);
    }
}