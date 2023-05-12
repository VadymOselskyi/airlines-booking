package io.skai.reservation.kafka;

import io.skai.reservation.dto.HistoryTicketDto;
import io.skai.reservation.kafka.config.KafkaProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HistoryTicketKafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final KafkaProperties properties;

    public void sendMessage(HistoryTicketDto ticketDto) {
        kafkaTemplate.send(properties.getTopic(), ticketDto);
    }
}