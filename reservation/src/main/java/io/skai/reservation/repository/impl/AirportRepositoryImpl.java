package io.skai.reservation.repository.impl;


import io.skai.reservation.jooq.Tables;
import io.skai.reservation.model.AirportModel;
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

    public AirportModel insert(AirportModel airport) {
        return dslContext.insertInto(Tables.AIRPORT, Tables.AIRPORT.NAME, Tables.AIRPORT.COUNTRY_CODE, Tables.AIRPORT.CITY)
                .values(airport.getName(), airport.getCountryCode(), airport.getCity())
                .returning()
                .fetchOneInto(AirportModel.class);
    }

    public List<AirportModel> selectAll() {
        return dslContext.selectFrom(Tables.AIRPORT)
                .fetchInto(AirportModel.class);
    }
    public AirportModel selectOneById(Long id) {
        return dslContext.selectFrom(Tables.AIRPORT)
                .where(Tables.AIRPORT.ID.eq(id))
                .fetchOneInto(AirportModel.class);
    }
}
