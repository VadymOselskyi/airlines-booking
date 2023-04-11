package io.skai.reservation.mapper;

import io.skai.reservation.dto.TicketDto;
import io.skai.reservation.jooq.tables.pojos.Ticket;
import org.mapstruct.Mapper;

@Mapper
public interface TicketMapper {

    Ticket ticketDtoToTicket(TicketDto dto);

    TicketDto ticketToTicketDto(Ticket ticket);
}
