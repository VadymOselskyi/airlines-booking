package io.skai.historicaldata.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import io.skai.historicaldata.kafka.KafkaProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ActiveProfiles;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class SpringIntegrationTest {

    @Autowired
    protected TicketClient ticketClient;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    protected KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    protected KafkaProperties kafkaProperties;
}