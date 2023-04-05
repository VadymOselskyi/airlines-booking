package io.skai.reservation.repository.impl;

import io.skai.reservation.model.FlightModel;
import io.skai.reservation.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FlightRepositoryImpl  {

    private final DSLContext dslContext;

//    @Override
//    public FlightModel insert(FlightModel flight) {
//        return dslContext.insertInto(Tables.FLIGHT)
//                .set(Tables.FLIGHT.ARRIVAL_AIRPORT_ID, flight.getArrivalAirportId())
//                .set(Tables.FLIGHT.DEPARTURE_AIRPORT_ID, flight.getDepartureAirportId())
//                .set(Tables.FLIGHT.ARRIVAL_DATE, flight.getArrivalDate())
//                .set(Tables.FLIGHT.DEPARTURE_DATE, flight.getDepartureDate())
//                .returning()
//                .fetchOneInto(FlightModel.class);
//    }
//
//    @Override
//    public List<FlightModel> selectAll() {
//        return dslContext.selectFrom(Tables.FLIGHT)
//                .fetchInto(FlightModel.class);
//    }
//
//    @Override
//    public FlightModel selectOneById(Long id) {
//        return dslContext.selectFrom(Tables.FLIGHT)
//                .where(Tables.FLIGHT.ID.eq(id))
//                .fetchOneInto(FlightModel.class);
//    }
}
