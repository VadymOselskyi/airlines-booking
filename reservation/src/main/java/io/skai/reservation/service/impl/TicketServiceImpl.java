package io.skai.reservation.service.impl;


import io.skai.reservation.model.TicketModel;
import io.skai.reservation.repository.TicketRepository;
import io.skai.reservation.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    @Override
    public TicketModel create(TicketModel ticket) {
        return ticketRepository.insert(ticket);
    }

    @Override
    public List<TicketModel> getAllTickets() {
        return ticketRepository.selectAll();
    }

    @Override
    public List<TicketModel> getAllTicketsByPassengerEmail(String email) {
        return null;
    }
}
