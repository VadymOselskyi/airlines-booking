package io.skai.reservation.repository.impl;

import io.skai.reservation.jooq.tables.pojos.Passenger;
import io.skai.reservation.repository.PassengerRepository;
import org.jooq.DSLContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static io.skai.reservation.jooq.Tables.PASSENGER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@ActiveProfiles("test")
class PassengerRepositoryImplTest {

    @Autowired
    private DSLContext dslContext;

    @Autowired
    private PassengerRepository passengerRepository;

    Passenger passenger;

    @BeforeEach
    void setUp() {
        dslContext.truncate(PASSENGER)
                .execute();
        passenger = new Passenger();
        passenger.setEmail("vo@gmail.com");
        passenger.setFirstName("Vadim");
        passenger.setLastName("Oselskyi");
        passenger.setPhone("097234395");
    }


    @Test
    void WhenInsertOneAirportTableShouldHasOneRowTest() {
        passengerRepository.insert(passenger);
        List<Passenger> expected = dslContext.selectFrom(PASSENGER).fetchInto(Passenger.class);

        assertThat(expected.size(), is(1));
        assertThat(expected, anything());
    }

    @Test
    void WhenInsertOneAirportTableShouldHasRightAirportTest() {
        Passenger insertedPassenger = passengerRepository.insert(passenger);
        List<Passenger> expected = dslContext.selectFrom(PASSENGER).fetchInto(Passenger.class);

        assertThat(expected.get(0), equalTo(insertedPassenger));
        assertThat(expected, contains(insertedPassenger));
    }

    @Test
    void whenNothingWasInsertedSelectAllShouldReturnEmptyListTest() {
        Passenger expected = passengerRepository.selectPassenger(1L);

        assertThat(expected, is(nullValue()));
    }

    @Test
    void whenInsertOneObjectSelectAllShouldReturnOneTest() {
        Passenger expected = dslContext.insertInto(PASSENGER)
                .set(PASSENGER.EMAIL, passenger.getEmail())
                .set(PASSENGER.FIRST_NAME, passenger.getFirstName())
                .set(PASSENGER.LAST_NAME, passenger.getLastName())
                .set(PASSENGER.PHONE, passenger.getPhone())
                .returning()
                .fetchOneInto(Passenger.class);

        Passenger actual = passengerRepository.selectPassenger(passenger.getEmail());

        assertThat(actual, equalTo(expected));
    }

    @Test
    void whenInsertOneObjectSelectAllShouldReturnRightTest() {
        Passenger expected = dslContext.insertInto(PASSENGER)
                .set(PASSENGER.EMAIL, passenger.getEmail())
                .set(PASSENGER.FIRST_NAME, passenger.getFirstName())
                .set(PASSENGER.LAST_NAME, passenger.getLastName())
                .set(PASSENGER.PHONE, passenger.getPhone())
                .returning()
                .fetchOneInto(Passenger.class);

        Passenger actual = passengerRepository.selectPassenger(passenger.getEmail());

        assertThat(actual, equalTo(expected));
    }
}