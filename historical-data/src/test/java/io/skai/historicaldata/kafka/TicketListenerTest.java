package io.skai.historicaldata.kafka;

import io.skai.historicaldata.BaseApplicationContextTest;
import io.skai.historicaldata.dto.HistoricalTicketDto;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

class TicketListenerTest extends BaseApplicationContextTest {

    private static final HistoricalTicketDto TICKET_DTO = createHistoricalTicketDto();

    @AfterEach
    void tearDown() {
        ticketRepository.deleteAll();
    }

    @Test
    void whenInputDtoIsValidTicketThenTicketServiceShouldSave() {
        kafkaTemplate().send(ticketTopic().name(), TICKET_DTO);

        await().atMost(7, SECONDS)
                .untilAsserted(() -> assertThat(ticketService.getTickets(TICKET_DTO.email()))
                        .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
                        .contains(TICKET_DTO));
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

    private static HistoricalTicketDto createHistoricalTicketDto() {
        return new HistoricalTicketDto(null, "TestName", "Test", "email@gmail.com",
                "123", "B12", new BigDecimal("123.00"),
                LocalDateTime.of(2023, 4, 17, 14, 20),
                "arrAir", "UA", "Kyiv",
                LocalDateTime.of(2023, 4, 17, 21, 35),
                "arrAir", "GB", "London");
    }
}