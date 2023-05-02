package io.skai.reservation.repository.impl;

import io.skai.reservation.jooq.tables.pojos.Flight;
import io.skai.reservation.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static io.skai.reservation.jooq.Tables.FLIGHT;

@Repository
@RequiredArgsConstructor
public class FlightRepositoryImpl implements FlightRepository {

    private final DSLContext dslContext;

    @Override
    public Flight insert(Flight flight) {
        return dslContext.insertInto(FLIGHT)
                .set(FLIGHT.ARRIVAL_AIRPORT_ID, flight.getArrivalAirportId())
                .set(FLIGHT.DEPARTURE_AIRPORT_ID, flight.getDepartureAirportId())
                .set(FLIGHT.ARRIVAL_DATE, flight.getArrivalDate())
                .set(FLIGHT.DEPARTURE_DATE, flight.getDepartureDate())
                .returning()
                .fetchOneInto(Flight.class);
    }

    @Override
    public List<Flight> selectAll() {
        return dslContext.selectFrom(FLIGHT)
                .fetchInto(Flight.class);
    }

    @Override
    public Flight get(Long flightId) {
        return dslContext.selectFrom(FLIGHT)
                .fetchOneInto(Flight.class);
    }
}