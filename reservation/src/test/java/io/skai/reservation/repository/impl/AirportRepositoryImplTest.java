package io.skai.reservation.repository.impl;

import io.skai.reservation.jooq.tables.pojos.Airport;
import io.skai.reservation.repository.AirportRepository;
import org.jooq.DSLContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static io.skai.reservation.jooq.Tables.AIRPORT;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class AirportRepositoryImplTest {

    @Autowired
    private DSLContext dslContext;
    @Autowired
    private AirportRepository airportRepository;
    Airport firstAirport;
    Airport secondAirport;

    @BeforeEach
    void setUp() {
        dslContext.truncate(AIRPORT)
                .execute();

        firstAirport = new Airport();
        firstAirport.setName("Main");
        firstAirport.setCountryCode("UA");
        firstAirport.setCity("Kyiv");

        secondAirport = new Airport();
        secondAirport.setName("West");
        secondAirport.setCountryCode("UA");
        secondAirport.setCity("Lviv");
    }

    @Test
    void whenInsertOneElementTableShouldHasOneRowTest() {
        Airport insertedAirport = airportRepository.insert(firstAirport);

        List<Airport> airports = dslContext.selectFrom(AIRPORT).fetchInto(Airport.class);

        assertThat(airports)
                .hasSize(1)
                .contains(insertedAirport);
    }

    @Test
    void whenInsertXElementTableShouldHasXRowTest() {
        Airport insertedAirport = airportRepository.insert(firstAirport);
        Airport secondInserted = airportRepository.insert(secondAirport);

        List<Airport> airports = dslContext.selectFrom(AIRPORT).fetchInto(Airport.class);

        assertThat(airports)
                .hasSize(2)
                .contains(insertedAirport, secondInserted)
                .doesNotHaveDuplicates();
    }

    @Test
    void whenNothingWasInsertedSelectAllShouldReturnEmptyListTest() {

        List<Airport> airports = airportRepository.selectAll();

        assertThat(airports).isEmpty();
    }

    @Test
    void whenInsertOneObjectSelectAllShouldReturnOneTest() {
        Airport expectedAirport = dslContext.insertInto(AIRPORT)
                .set(AIRPORT.NAME, "airport1")
                .set(AIRPORT.COUNTRY_CODE, "UA")
                .set(AIRPORT.CITY, "Kyiv")
                .returning()
                .fetchOneInto(Airport.class);

        List<Airport> airports = airportRepository.selectAll();

        assertThat(airports)
                .hasSize(1)
                .contains(expectedAirport);
    }

    @Test
    void whenInsertMoreThanOneObjectSelectAllShouldReturnMoreThanOneTest() {
        var expectedAirport = dslContext.insertInto(AIRPORT, AIRPORT.NAME, AIRPORT.COUNTRY_CODE, AIRPORT.CITY)
                .values("airport1", "UA", "Lviv")
                .values("International", "FRA", "Paris")
                .returning()
                .fetchInto(Airport.class);

        List<Airport> airports = airportRepository.selectAll();

        assertThat(airports)
                .hasSize(2)
                .isEqualTo(expectedAirport);
    }

}