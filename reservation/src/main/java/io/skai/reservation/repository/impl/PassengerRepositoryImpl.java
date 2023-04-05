package io.skai.reservation.repository.impl;

import io.skai.reservation.model.PassengerModel;
import io.skai.reservation.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static io.skai.reservation.jooq.Tables.PASSENGER;


@Repository
@RequiredArgsConstructor
public class PassengerRepositoryImpl implements PassengerRepository {

    private final DSLContext dslContext;

    @Override
    public PassengerModel insert(PassengerModel passenger) {
        return dslContext.insertInto(PASSENGER)
                .set(PASSENGER.EMAIL, passenger.getEmail())
                .set(PASSENGER.FIRST_NAME, passenger.getFirstName())
                .set(PASSENGER.LAST_NAME, passenger.getLastName())
                .set(PASSENGER.PHONE, passenger.getPhone())
                .returning()
                .fetchOneInto(PassengerModel.class);
    }

    @Override
    public PassengerModel selectPassenger(String email) {
        return dslContext.selectFrom(PASSENGER)
                .where(PASSENGER.EMAIL.eq(email))
                .fetchOneInto(PassengerModel.class);
    }

    @Override
    public PassengerModel selectPassenger(Long id) {
        return dslContext.selectFrom(PASSENGER)
                .where(PASSENGER.ID.eq(id))
                .fetchOneInto(PassengerModel.class);    }
}
