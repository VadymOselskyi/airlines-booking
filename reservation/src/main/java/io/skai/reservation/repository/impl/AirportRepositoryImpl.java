package io.skai.reservation.repository.impl;

import io.skai.reservation.jooq.Tables;
import io.skai.reservation.jooq.tables.pojos.Airport;
import io.skai.reservation.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AirportRepositoryImpl implements AirportRepository {
    private final DSLContext dslContext;

    public Airport insert(String name, String countryCode, String city) {
        return dslContext.insertInto(Tables.AIRPORT, Tables.AIRPORT.NAME, Tables.AIRPORT.COUNTRY_CODE, Tables.AIRPORT.CITY)
                .values(name, countryCode, city)
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