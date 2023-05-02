package io.skai.reservation;

import io.skai.reservation.dto.HistoryTicketDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class TestListener {

    private HistoryTicketDto ticketDto;
    private final CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.group-id}")
    public void listen(HistoryTicketDto dto) {
        ticketDto = dto;
        latch.countDown();
    }

    public HistoryTicketDto getTicketDto() {
        return ticketDto;
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}