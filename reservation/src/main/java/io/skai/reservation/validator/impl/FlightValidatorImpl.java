package io.skai.reservation.validator.impl;

import io.skai.reservation.exception.EntityDoesNotExistsException;
import io.skai.reservation.validator.FlightValidator;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

import static io.skai.reservation.jooq.Tables.FLIGHT;

@Component
@RequiredArgsConstructor
public class FlightValidatorImpl implements FlightValidator {

    private final DSLContext dslContext;

    @Override
    public void validate(long id) {
        dslContext.selectFrom(FLIGHT)
                .where(FLIGHT.ID.eq(id))
                .fetchOptional()
                .orElseThrow(() -> new EntityDoesNotExistsException("Flight with id " + id + " does not exist"));
    }
}
