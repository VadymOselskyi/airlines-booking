package io.skai.historicaldata.cucumber.steps;

import io.cucumber.java.After;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.skai.historicaldata.cucumber.SpringIntegrationTest;
import io.skai.historicaldata.dto.HistoricalTicketDto;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.assertj.core.api.Assertions;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class TicketSteps extends SpringIntegrationTest {

    @Given("create tickets")
    public void sendTicketsToTopic(List<HistoricalTicketDto> dtos) {
        dtos.forEach(dto -> kafkaTemplate().send(ticketTopic().name(), dto));
    }

    @When("wait {int} seconds")
    public void waiting(int pause) throws InterruptedException {
        Thread.sleep(pause * 1000L);
    }

    @Then("client can get all Tickets by {string}")
    public void theClientReceiveListOfAirportDto(String email, List<HistoricalTicketDto> airportDtos) {
        List<HistoricalTicketDto> historyTickets = ticketClient.getHistoryTickets(email);
        Assertions.assertThat(historyTickets)
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
                .isEqualTo(airportDtos);
    }

    @After
    public void cleanDatabase() {
        jdbcTemplate.execute("TRUNCATE TABLE `airlines-db`.history_ticket");
    }

    @DataTableType(replaceWithEmptyString = "[null]")
    public HistoricalTicketDto ticketTransformer(Map<String, String> row) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Long id = row.get("id") != null ? Long.parseLong(row.get("id")) : null;
        return new HistoricalTicketDto(
                id,
                row.get("firstName"),
                row.get("lastName"),
                row.get("email"),
                row.get("phone"),
                row.get("seatNumber"),
                new BigDecimal(row.get("price")),
                LocalDateTime.parse(row.get("departureDate"), formatter),
                row.get("departureAirportName"),
                row.get("departureCountryCode"),
                row.get("departureCity"),
                LocalDateTime.parse(row.get("arrivalDate"), formatter),
                row.get("arrivalAirportName"),
                row.get("arrivalCountryCode"),
                row.get("arrivalCity")
        );
    }

    private KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(createProducer());
    }

    private ProducerFactory<String, Object> createProducer() {
        Map<String, Object> configProps = Map.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootStrapServers(),
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    private NewTopic ticketTopic() {
        return TopicBuilder.name(kafkaProperties.getTopic()).build();
    }
}