package io.skai.reservation.repository.impl;

import io.skai.reservation.BaseApplicationContextTest;
import io.skai.reservation.jooq.tables.pojos.Airport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.skai.reservation.jooq.Tables.AIRPORT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;

class AirportRepositoryImplTest extends BaseApplicationContextTest {

    private final Airport FIRST_AIRPORT = new Airport(1L, "Main International", "UA", "Kyiv");
    private final Airport SECOND_AIRPORT = new Airport(2L, "South international airport", "UA", "Odesa");

    @AfterEach
    void tearDown() {
        dslContext.truncate(AIRPORT)
                .execute();
    }

    @Test
    void whenNothingWasInsertedSelectAllShouldReturnEmptyList() {
        List<Airport> airports = airportRepository.selectAll();

        assertThat(airports, empty());
    }

    @Test
    void whenInsertOneObjectSelectAllShouldReturnOne() {
        Airport expectedAirport = airportRepository
                .insert(FIRST_AIRPORT.getName(), FIRST_AIRPORT.getCountryCode(), FIRST_AIRPORT.getCity());

        List<Airport> airports = airportRepository.selectAll();

        assertThat(airports, contains(expectedAirport));
    }

    @Test
    void whenInsertMoreThanOneObjectSelectAllShouldReturnMoreThanOne() {
        var expectedAirport = airportRepository
                .insert(FIRST_AIRPORT.getName(), FIRST_AIRPORT.getCountryCode(), FIRST_AIRPORT.getCity());
        var expectedAirport2 = airportRepository
                .insert(SECOND_AIRPORT.getName(), SECOND_AIRPORT.getCountryCode(), SECOND_AIRPORT.getCity());

        List<Airport> airports = airportRepository.selectAll();

        assertThat(airports, contains(expectedAirport, expectedAirport2));
    }
}