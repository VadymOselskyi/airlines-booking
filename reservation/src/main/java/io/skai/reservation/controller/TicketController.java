package io.skai.reservation.controller;

import io.skai.reservation.client.TicketClient;
import io.skai.reservation.dto.HistoryTicketDto;
import io.skai.reservation.dto.TicketDto;
import io.skai.reservation.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final TicketClient ticketClient;

    @PostMapping
    public ResponseEntity<TicketDto> save(@RequestBody TicketDto ticket) {

        return ResponseEntity.ok(ticketService.create(ticket));
    }

    @GetMapping
    public ResponseEntity<List<TicketDto>> getAll() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping("/history")
    public ResponseEntity<List<HistoryTicketDto>> getAllByPassengerEmail(@RequestParam String email) {
        List<HistoryTicketDto> tickets = ticketClient.getHistoryTickets(email);
        return ResponseEntity.ok(tickets);
    }
}