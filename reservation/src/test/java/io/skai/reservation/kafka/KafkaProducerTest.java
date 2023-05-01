package io.skai.reservation.kafka;

import io.skai.reservation.BaseApplicationContextTest;
import io.skai.reservation.dto.HistoryTicketDto;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class KafkaProducerTest extends BaseApplicationContextTest {

    private static final HistoryTicketDto HISTORY_TICKET_DTO = createHistoryTicketDto();

    @Test
    void firstProducerTest() {
        Consumer<String, HistoryTicketDto> kafkaConsumer = createKafkaConsumer().createConsumer();
        kafkaConsumer.subscribe(Collections.singleton(kafkaProperties.getTopic()));
        kafkaProducer.sendMessage(HISTORY_TICKET_DTO);

        HistoryTicketDto historyTicketDto = KafkaTestUtils
                .getSingleRecord(kafkaConsumer, kafkaProperties.getTopic())
                .value();

        assertThat(historyTicketDto).isEqualTo(HISTORY_TICKET_DTO);
    }

    private DefaultKafkaConsumerFactory<String, HistoryTicketDto> createKafkaConsumer() {
        return new DefaultKafkaConsumerFactory<>(Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootStrapServers(),
                ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getGroupId(),
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class,
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"),
                new StringDeserializer(),
                new JsonDeserializer<>(HistoryTicketDto.class));
    }

    private static HistoryTicketDto createHistoryTicketDto() {
        return new HistoryTicketDto("Tony", "Stark", "ironman@gmail.com", "380942847", "B12", new BigDecimal(120),
                LocalDateTime.of(2023, 4, 11, 5, 40), "NEW YORK INTERNATIONAL", "US", "New York",
                LocalDateTime.of(2023, 4, 12, 8, 30), " LVIV International", "UA", "Lviv");
    }
}