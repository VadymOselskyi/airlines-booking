package io.skai.historicaldata.cucumber;

import io.skai.historicaldata.dto.HistoricalTicketDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "tickets", url = "http://localhost:8081")
public interface TicketClient {

    @GetMapping("/api/tickets")
    List<HistoricalTicketDto> getHistoryTickets(@RequestParam("email") String email);
}