package io.skai.historicaldata.kafka;

import io.skai.historicaldata.dto.HistoricalTicketDto;
import io.skai.historicaldata.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Listener {

    private final TicketService ticketService;

    @KafkaListener(topics = "ticket_topic", groupId = "groupId")
    public void listen(HistoricalTicketDto dto) {
        ticketService.saveTicket(dto);
    }
}
