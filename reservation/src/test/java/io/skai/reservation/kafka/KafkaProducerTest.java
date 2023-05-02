package io.skai.reservation.kafka;

import io.skai.reservation.BaseApplicationContextTest;
import io.skai.reservation.dto.HistoryTicketDto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

class KafkaProducerTest extends BaseApplicationContextTest {

    private static final HistoryTicketDto HISTORY_TICKET_DTO = createHistoryTicketDto();

    @Test
    void whenProducerSendMessageThenConsumerShouldConsumeThem() throws InterruptedException {
        kafkaProducer.sendMessage(HISTORY_TICKET_DTO);

        boolean consumedMessage = testListener.getLatch().await(7, TimeUnit.SECONDS);

        assertThat(consumedMessage).isTrue();
        assertThat(testListener.getTicketDto()).isEqualTo(HISTORY_TICKET_DTO);
    }

    private static HistoryTicketDto createHistoryTicketDto() {
        return new HistoryTicketDto("Tony", "Stark", "ironman@gmail.com", "380942847", "B12", new BigDecimal(120),
                LocalDateTime.of(2023, 4, 11, 5, 40), "NEW YORK INTERNATIONAL", "US", "New York",
                LocalDateTime.of(2023, 4, 12, 8, 30), " LVIV International", "UA", "Lviv");
    }
}