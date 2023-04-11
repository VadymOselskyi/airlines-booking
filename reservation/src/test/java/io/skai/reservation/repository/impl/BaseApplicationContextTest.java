package io.skai.reservation.repository.impl;

import io.skai.reservation.repository.AirportRepository;
import io.skai.reservation.repository.FlightRepository;
import io.skai.reservation.repository.PassengerRepository;
import io.skai.reservation.repository.TicketRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class BaseApplicationContextTest {

    @Autowired
    protected DSLContext dslContext;
    @Autowired
    protected AirportRepository airportRepository;
    @Autowired
    protected PassengerRepository passengerRepository;
    @Autowired
    protected FlightRepository flightRepository;
    @Autowired
    protected TicketRepository ticketRepository;
}
