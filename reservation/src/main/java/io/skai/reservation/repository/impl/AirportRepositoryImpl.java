package io.skai.reservation.repository.impl;

import io.skai.reservation.jooq.Tables;
import io.skai.reservation.jooq.tables.pojos.Airport;
import io.skai.reservation.repository.AirportRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
@RequiredArgsConstructor
public class AirportRepositoryImpl implements AirportRepository {
    private final DSLContext dslContext;

    public Airport insert(Airport airport) {
        return dslContext.insertInto(Tables.AIRPORT, Tables.AIRPORT.NAME, Tables.AIRPORT.COUNTRY_CODE, Tables.AIRPORT.CITY)
                .values(airport.getName(), airport.getCountryCode(), airport.getCity())
                .returning()
                .fetchOneInto(Airport.class);
    }

    public List<Airport> selectAll() {
        return dslContext.selectFrom(Tables.AIRPORT)
                .fetchInto(Airport.class);
    }

    public Airport selectOneById(Long id) {
        return dslContext.selectFrom(Tables.AIRPORT)
                .where(Tables.AIRPORT.ID.eq(id))
                .fetchOneInto(Airport.class);
    }
}
