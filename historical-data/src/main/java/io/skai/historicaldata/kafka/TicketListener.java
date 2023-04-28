package io.skai.historicaldata.kafka;

import io.skai.historicaldata.dto.HistoricalTicketDto;
import io.skai.historicaldata.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TicketListener {

    private final TicketService ticketService;

    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.group-id}")
    public void listen(HistoricalTicketDto dto) {
        try {
            ticketService.saveTicket(dto);
        } catch (Exception exception) {
            log.error("{} was not saved" , dto, exception);
        }
    }
}