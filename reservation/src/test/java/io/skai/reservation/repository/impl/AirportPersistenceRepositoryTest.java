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
        List<Airport> airports = airportPersistenceRepository.findAll();

        assertThat(airports, empty());
    }

    @Test
    void whenCreatedTwoAirportsThenGetAllShouldReturnThem() {
        var createKyivCommand = createAirportCommand(KYIV_AIRPORT);
        var createBoryspilCommand = createAirportCommand(BORYSPIL_AIRPORT);

        airportPersistenceRepository.save(List.of(createKyivCommand, createBoryspilCommand));
        List<Airport> airports = airportPersistenceRepository.findAll();

        assertThat(airports, containsInAnyOrder(KYIV_AIRPORT, BORYSPIL_AIRPORT));
    }

    @Test
    void whenCreatedTwoAirportsThenGetShouldReturnRightAirportById() {
        var createKyivCommand = createAirportCommand(KYIV_AIRPORT);
        var createBoryspilCommand = createAirportCommand(BORYSPIL_AIRPORT);

        airportPersistenceRepository.save(List.of(createKyivCommand, createBoryspilCommand));
        Airport airport = airportPersistenceRepository.findOne(BORYSPIL_AIRPORT.getId());

        assertThat(airport, equalTo(BORYSPIL_AIRPORT));
    }

    private CreateAirportCommand createAirportCommand(Airport airport) {
        var cmd = new CreateAirportCommand();
        cmd.set(NAME, airport.getName());
        cmd.set(COUNTRY_CODE, airport.getCountryCode());
        cmd.set(CITY, airport.getCity());
        return cmd;
    }
}