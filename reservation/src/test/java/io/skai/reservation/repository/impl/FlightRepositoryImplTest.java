package io.skai.reservation.repository.impl;

import io.skai.reservation.BaseApplicationContextTest;
import io.skai.reservation.jooq.Tables;
import io.skai.reservation.jooq.tables.pojos.Flight;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class FlightRepositoryImplTest extends BaseApplicationContextTest {

    @AfterEach
    void tearDown() {
        dslContext.truncate(Tables.FLIGHT)
                .execute();
    }

    @Test
    void whenNothingWasInsertedThenSelectAllShouldReturnEmptyList(){
        List<Flight> flights = flightRepository.selectAll();

        assertThat(flights, empty());
    }

    @Test
    void whenInsertOneFlightThenTableShouldHasOneItem() {
        Flight flight = new Flight(1L, LocalDateTime.of(2023, 4, 11, 8, 40),
                1L, LocalDateTime.of(2023, 4, 11, 8, 40), 2L);

        Flight insertedFlight = flightRepository.insert(flight);
        List<Flight> flights = flightRepository.selectAll();

        assertThat(flights, contains(insertedFlight));
    }

}