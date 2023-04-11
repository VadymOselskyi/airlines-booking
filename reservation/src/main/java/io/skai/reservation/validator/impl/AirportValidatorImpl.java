package io.skai.reservation.validator.impl;

import io.skai.reservation.exception.EntityDoesNotExistsException;
import io.skai.reservation.validator.AirportValidator;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

import static io.skai.reservation.jooq.Tables.AIRPORT;

@Component
@RequiredArgsConstructor
public class AirportValidatorImpl implements AirportValidator {

    private final DSLContext dslContext;

    @Override
    public void validate(long departureAirportId, long arrivalAirportId) {
        dslContext.selectFrom(AIRPORT)
                .where(AIRPORT.ID.eq(departureAirportId)
                        .and(AIRPORT.ID.eq(arrivalAirportId)))
                .fetchOptional()
                .orElseThrow(() -> new EntityDoesNotExistsException("Airports with ids:"
                        + departureAirportId + " or " + arrivalAirportId
                        + " are not exists"));
    }
}
