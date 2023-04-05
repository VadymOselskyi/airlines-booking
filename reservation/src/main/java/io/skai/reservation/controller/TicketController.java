package io.skai.reservation.controller;

import io.skai.reservation.consumer.TicketClient;
import io.skai.reservation.model.PassengerModel;
import io.skai.reservation.model.TicketModel;
import io.skai.reservation.service.PassengerService;
import io.skai.reservation.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;
    private final PassengerService passengerService;
    private final TicketClient consumer;

    @PostMapping("/new")
    public ResponseEntity<TicketModel> save(@RequestBody TicketModel ticket) {

        return ResponseEntity.ok(ticketService.create(ticket));
    }

    @GetMapping
    public ResponseEntity<List<TicketModel>> getAll() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping("/history")
    public ResponseEntity<List<TicketModel>> getAllByPassengerEmail(@RequestParam String email) {
        PassengerModel passenger = passengerService.getPassengerByEmail(email);
        var tickets = consumer.getTicketsByUserId(passenger.getId());
        return ResponseEntity.ok(tickets);
    }
}
