package io.skai.reservation.repository;

import io.skai.reservation.BaseApplicationContextTest;
import io.skai.reservation.model.PassengerModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static io.skai.reservation.jooq.Tables.PASSENGER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class PassengerHibernateRepositoryTest extends BaseApplicationContextTest {

    private final PassengerModel passengerModel = new PassengerModel(1L, "Name", "Surname", "ns@gmail.com", "12345");

    @AfterEach
    void tearDown() {
        dslContext.truncate(PASSENGER)
                .execute();
    }

    @Test
    void whenInsertOneAirportTableShouldHasRightAirport() {
        PassengerModel insertedPassenger = passengerHibernateRepository.save(passengerModel);
        PassengerModel expected = passengerHibernateRepository.findById(insertedPassenger.getId()).orElse(null);

        assertThat(expected, equalTo(insertedPassenger));
    }

    @Test
    void whenNothingWasInsertedSelectAllShouldReturnNull() {
        PassengerModel expected = passengerHibernateRepository.findById(1L).orElse(null);

        assertThat(expected, is(nullValue()));
    }
}