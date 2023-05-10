package io.skai.historicaldata.cucumber.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.skai.historicaldata.cucumber.SpringIntegrationTest;
import io.skai.historicaldata.dto.HistoricalTicketDto;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.assertj.core.api.Assertions;
import org.awaitility.Awaitility;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class StepDefinitions extends SpringIntegrationTest {

    @Value("${cucumber.url}")
    private String getTicketsUrl;
    ResponseEntity<List<HistoricalTicketDto>> lastResponse;

    @Given("create tickets")
    public void sendTicketsToTopic(List<HistoricalTicketDto> dtos) {
        dtos.forEach(dto -> kafkaTemplate().send(ticketTopic().name(), dto));
    }

    @And("wait {int} seconds")
    public void waiting(int pause) {
        Awaitility.await().atMost(pause, TimeUnit.SECONDS);
    }

    @When("^client get tickets$")
    public void whenClientCallsGetTickets() {
        lastResponse = testRestTemplate
                .exchange(getTicketsUrl, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
    }

    @Then("client receives list of Tickets")
    public void theClientReceiveListOfAirportDto(List<HistoricalTicketDto> airportDtos) {
        Assertions.assertThat(airportDtos).isEqualTo(lastResponse.getBody());
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