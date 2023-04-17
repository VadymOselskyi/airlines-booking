package io.skai.reservation.repository;

import io.skai.reservation.BaseApplicationContextTest;
import io.skai.reservation.model.Passenger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static io.skai.reservation.jooq.Tables.PASSENGER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class PassengerRepositoryTest extends BaseApplicationContextTest {

    private final Passenger passenger = new Passenger(1L, "Name", "Surname", "ns@gmail.com", "12345");

    @AfterEach
    void tearDown() {
        dslContext.truncate(PASSENGER)
                .execute();
    }

    @Test
    void whenInsertOneAirportTableShouldHasRightAirport() {
        Passenger insertedPassenger = passengerRepository.save(passenger);
        Passenger expected = passengerRepository.findById(insertedPassenger.getId()).orElse(null);

        assertThat(expected, equalTo(insertedPassenger));
    }

    @Test
    void whenNothingWasInsertedSelectAllShouldReturnNull() {
        Passenger expected = passengerRepository.findById(1L).orElse(null);

        assertThat(expected, is(nullValue()));
    }
}