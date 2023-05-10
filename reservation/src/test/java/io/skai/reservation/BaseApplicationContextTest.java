package io.skai.reservation;

import com.kenshoo.pl.entity.PersistenceLayer;
import io.skai.reservation.kafka.HistoryTicketKafkaProducer;
import io.skai.reservation.kafka.config.KafkaProperties;
import io.skai.reservation.pl.AirportEntity;
import io.skai.reservation.repository.*;
import io.skai.reservation.repository.impl.AirportPersistenceRepository;
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
    @Autowired
    protected AirportPersistenceRepository airportPersistenceRepository;
    @Autowired
    protected PersistenceLayer<AirportEntity> persistenceLayer;
    @Autowired
    protected HistoryTicketKafkaProducer historyTicketKafkaProducer;
    @Autowired
    protected KafkaProperties kafkaProperties;
    @Autowired
    protected TestListener testListener;
}