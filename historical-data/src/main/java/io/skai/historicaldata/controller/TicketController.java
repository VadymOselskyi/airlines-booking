package io.skai.historicaldata.controller;

import io.skai.historicaldata.model.Ticket;
import io.skai.historicaldata.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/{userId}")
    public List<Ticket> greetingTicket(@PathVariable Long userId) {
        Ticket ticket = new Ticket(userId, 1L, 1L, new BigDecimal(12));
        return List.of(ticket);
//        return ticketService.getTicketByUserId(userId);
    }
}
