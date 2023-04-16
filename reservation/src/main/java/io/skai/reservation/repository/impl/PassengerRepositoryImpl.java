package io.skai.reservation.repository.impl;

import io.skai.reservation.jooq.tables.pojos.Passenger;
import io.skai.reservation.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static io.skai.reservation.jooq.Tables.PASSENGER;


@Repository
@RequiredArgsConstructor
public class PassengerRepositoryImpl implements PassengerRepository {

    private final DSLContext dslContext;

    @Override
    public Passenger insert(Passenger passenger) {
        return dslContext.insertInto(PASSENGER)
                .set(PASSENGER.EMAIL, passenger.getEmail())
                .set(PASSENGER.FIRST_NAME, passenger.getFirstName())
                .set(PASSENGER.LAST_NAME, passenger.getLastName())
                .set(PASSENGER.PHONE, passenger.getPhone())
                .returning()
                .fetchOneInto(Passenger.class);
    }

    @Override
    public List<Passenger> selectPassengers() {
        return dslContext.selectFrom(PASSENGER)
                .fetchInto(Passenger.class);
    }

    @Override
    public Passenger selectPassenger(Long id) {
        return dslContext.selectFrom(PASSENGER)
                .where(PASSENGER.ID.eq(id))
                .fetchOneInto(Passenger.class);
    }
}