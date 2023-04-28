package io.skai.reservation.kafka;

import io.skai.reservation.kafka.config.KafkaProperties;
import io.skai.reservation.service.DbArchiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final DbArchiveService dbArchiveService;
    private final KafkaProperties properties;

    public void sendMessage() {
        dbArchiveService.getForArchive().forEach(dto -> kafkaTemplate.send(properties.getTopic() , dto));
    }
}