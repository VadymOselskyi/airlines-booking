package io.skai.historicaldata.controller;

import io.skai.historicaldata.dto.HistoricalTicketDto;
import io.skai.historicaldata.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public void create(@RequestBody HistoricalTicketDto historicalTicketDto) {
        ticketService.saveTicket(historicalTicketDto);
    }

    @GetMapping("/user")
    public List<HistoricalTicketDto> getTickets(@RequestParam String email) {

        return ticketService.getTickets(email);
    }
}
