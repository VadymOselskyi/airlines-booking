package io.skai.reservation.repository.impl;

import io.skai.reservation.BaseApplicationContextTest;
import io.skai.reservation.jooq.tables.pojos.Passenger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static io.skai.reservation.jooq.Tables.PASSENGER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class PassengerRepositoryImplTest extends BaseApplicationContextTest {

    private final Passenger passenger = new Passenger(1L, "vo@gmail.com", "Vadim", "Oselskyi", "097234395");

    @AfterEach
    void tearDown() {
        dslContext.truncate(PASSENGER)
                .execute();
    }

    @Test
    void whenInsertOneAirportTableShouldHasRightAirport() {
        Passenger insertedPassenger = passengerRepository.insert(passenger);
        Passenger expected = passengerRepository.selectPassenger(insertedPassenger.getId());

        assertThat(expected, equalTo(insertedPassenger));
    }

    @Test
    void whenNothingWasInsertedSelectAllShouldReturnNull() {
        Passenger expected = passengerRepository.selectPassenger(1L);

        assertThat(expected, is(nullValue()));
    }
}