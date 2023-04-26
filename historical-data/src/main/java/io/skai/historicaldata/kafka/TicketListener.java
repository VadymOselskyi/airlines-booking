package io.skai.historicaldata.kafka;

import io.skai.historicaldata.dto.HistoricalTicketDto;
import io.skai.historicaldata.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketListener {

    private final TicketService ticketService;

    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.group-id}")
    public void listen(HistoricalTicketDto dto) {
        ticketService.saveTicket(dto);
    }
}