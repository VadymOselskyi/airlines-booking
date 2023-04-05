package io.skai.historicaldata.service;


import io.skai.historicaldata.model.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService{

//    private final TicketRepository ticketRepository;

    @Override
    public List<Ticket> getTicketByUserId(Long userId) {
//        return ticketRepository.findByUserId(userId);
    return new ArrayList<>();
    }

    @Override
    public Ticket saveTicket(Ticket ticket) {
//        return ticketRepository.save(ticket);
        return new Ticket();
    }
}
