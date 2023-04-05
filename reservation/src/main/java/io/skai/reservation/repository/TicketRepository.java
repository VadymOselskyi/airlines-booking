package io.skai.reservation.repository;



import io.skai.reservation.model.TicketModel;

import java.util.List;

public interface TicketRepository {
    TicketModel insert(TicketModel ticket);
    List<TicketModel> selectAll();
}
