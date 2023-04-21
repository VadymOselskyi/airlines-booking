package io.skai.reservation.repository.impl;

import io.skai.reservation.BaseApplicationContextTest;
import io.skai.reservation.model.Airport;
import io.skai.reservation.pl.command.CreateAirportCommand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.skai.reservation.jooq.Tables.AIRPORT;
import static io.skai.reservation.pl.AirportEntity.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class AirportPersistenceRepositoryTest extends BaseApplicationContextTest {

    private static final Airport KYIV_AIRPORT = new Airport(1L, "Main International", "UA", "Kyiv");
    private static final Airport BORYSPIL_AIRPORT = new Airport(2L, "Airport Boryspil", "UA", "Boryspil");

    @AfterEach
    void tearDown() {
        dslContext.truncate(AIRPORT)
                .execute();
    }

    @Test
    void whenNothingWasInsertedSelectAllShouldReturnEmptyList() {
        List<Airport> airports = airportPersistenceRepository.selectAll();

        assertThat(airports, empty());
    }

    @Test
    void whenCreatedTwoAirportsThenGetAllShouldReturnThem() {
        var cmd = new CreateAirportCommand();
        cmd.set(NAME, KYIV_AIRPORT.getName());
        cmd.set(COUNTRY_CODE, KYIV_AIRPORT.getCountryCode());
        cmd.set(CITY, KYIV_AIRPORT.getCity());
        var cmd2 = new CreateAirportCommand();
        cmd2.set(NAME, BORYSPIL_AIRPORT.getName());
        cmd2.set(COUNTRY_CODE, BORYSPIL_AIRPORT.getCountryCode());
        cmd2.set(CITY, BORYSPIL_AIRPORT.getCity());

        airportPersistenceRepository.insert(List.of(cmd, cmd2));
        List<Airport> airports = airportPersistenceRepository.selectAll();

        assertThat(airports, contains(KYIV_AIRPORT, BORYSPIL_AIRPORT));
    }

    @Test
    void whenCreatedTwoAirportsThenGetShouldReturnRightAirportById() {
        var cmd = new CreateAirportCommand();
        cmd.set(NAME, KYIV_AIRPORT.getName());
        cmd.set(COUNTRY_CODE, KYIV_AIRPORT.getCountryCode());
        cmd.set(CITY, KYIV_AIRPORT.getCity());
        var cmd2 = new CreateAirportCommand();
        cmd2.set(NAME, BORYSPIL_AIRPORT.getName());
        cmd2.set(COUNTRY_CODE, BORYSPIL_AIRPORT.getCountryCode());
        cmd2.set(CITY, BORYSPIL_AIRPORT.getCity());

        airportPersistenceRepository.insert(List.of(cmd, cmd2));
        Airport airport = airportPersistenceRepository.selectOne(BORYSPIL_AIRPORT.getId());

        assertThat(airport, equalTo(BORYSPIL_AIRPORT));
    }
}