package io.skai.reservation.validator.impl;

import io.skai.reservation.exception.EntityDoesNotExistsException;
import io.skai.reservation.validator.PassengerValidator;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

import static io.skai.reservation.jooq.Tables.PASSENGER;

@Component
@RequiredArgsConstructor
public class PassengerValidatorImpl implements PassengerValidator {

    private final DSLContext dslContext;

    @Override
    public void validate(long id) {
        dslContext.selectFrom(PASSENGER)
                .where(PASSENGER.ID.eq(id))
                .fetchOptional()
                .orElseThrow(() -> new EntityDoesNotExistsException("Passenger with id " + id + " does not exist"));
    }
}
