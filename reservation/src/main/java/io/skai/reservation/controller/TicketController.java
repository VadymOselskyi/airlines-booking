package io.skai.reservation.controller;

import io.skai.reservation.client.TicketClient;
import io.skai.reservation.dto.PassengerDto;
import io.skai.reservation.dto.TicketDto;
import io.skai.reservation.service.PassengerService;
import io.skai.reservation.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final PassengerService passengerService;
    private final TicketClient client;

    @PostMapping
    public ResponseEntity<TicketDto> save(@RequestBody TicketDto ticket) {

        return ResponseEntity.ok(ticketService.create(ticket));
    }

    @GetMapping
    public ResponseEntity<List<TicketDto>> getAll() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping("/history")
    public ResponseEntity<List<TicketDto>> getAllByPassengerEmail(@RequestParam String email) {
        PassengerDto passenger = passengerService.getPassengerByEmail(email);
        var tickets = client.getTicketsByUserId(passenger.id());
        return ResponseEntity.ok(tickets);
    }
}
