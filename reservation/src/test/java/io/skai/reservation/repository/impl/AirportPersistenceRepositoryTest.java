package io.skai.reservation.repository.impl;

import io.skai.reservation.BaseApplicationContextTest;
import io.skai.reservation.model.Airport;
import io.skai.reservation.pl.command.CreateAirportCommand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.skai.reservation.jooq.Tables.AIRPORT;
import static io.skai.reservation.pl.AirportEntity.*;
import static org.assertj.core.api.Assertions.assertThat;

class AirportPersistenceRepositoryTest extends BaseApplicationContextTest {

    private static final Airport KYIV_AIRPORT = new Airport(1L, "Main International", "UA", "Kyiv");
    private static final Airport BORYSPIL_AIRPORT = new Airport(2L, "Airport Boryspil", "UA", "Boryspil");

    @AfterEach
    void tearDown() {
        dslContext.truncate(AIRPORT)
                .execute();
    }

    @Test
    void whenNothingWasInsertedFindAllShouldReturnEmptyList() {
        List<Airport> airports = airportPersistenceRepository.findAll();

        assertThat(airports).isEmpty();
    }

    @Test
    void whenCreatedTwoAirportsThenFindAllShouldReturnThem() {
        var createKyivCommand = createAirportCommand(KYIV_AIRPORT);
        var createBoryspilCommand = createAirportCommand(BORYSPIL_AIRPORT);

        airportPersistenceRepository.save(List.of(createKyivCommand, createBoryspilCommand));
        List<Airport> airports = airportPersistenceRepository.findAll();

        assertThat(airports).hasSize(2)
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
                .contains(KYIV_AIRPORT, BORYSPIL_AIRPORT);
    }

    @Test
    void whenCreatedTwoAirportsThenFindOneShouldReturnRightAirportById() {
        var createKyivCommand = createAirportCommand(KYIV_AIRPORT);
        var createBoryspilCommand = createAirportCommand(BORYSPIL_AIRPORT);

        List<Airport> airports = airportPersistenceRepository.save(List.of(createKyivCommand, createBoryspilCommand));

        airports.forEach(expectedAirport -> {
            Airport actualAirport = airportPersistenceRepository.findOne(expectedAirport.getId());

            assertThat(expectedAirport).isEqualTo(actualAirport);
        });
    }

    private CreateAirportCommand createAirportCommand(Airport airport) {
        var command = new CreateAirportCommand();
        command.set(NAME, airport.getName());
        command.set(COUNTRY_CODE, airport.getCountryCode());
        command.set(CITY, airport.getCity());
        return command;
    }
}