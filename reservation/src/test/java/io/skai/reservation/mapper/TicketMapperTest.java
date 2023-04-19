package io.skai.reservation.mapper;

import io.skai.reservation.dto.TicketDto;
import io.skai.reservation.jooq.tables.pojos.Ticket;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class TicketMapperTest {
    private static final Ticket TICKET = new Ticket(4L, 15L, 81L, new BigDecimal(120), "51B");
    private static final TicketDto TICKET_DTO = new TicketDto(4L, 15L, 81L, new BigDecimal(120), "51B");

    private final TicketMapper ticketMapper = Mappers.getMapper(TicketMapper.class);

    @Test
    void whenInputTicketThenReturnTicketDto(){
        TicketDto actualDto = ticketMapper.ticketToTicketDto(TICKET);

        assertThat(actualDto).isEqualTo(TICKET_DTO);
    }

    @Test
    void whenInputTicketDtoThenReturnTicket(){
        Ticket actual = ticketMapper.ticketDtoToTicket(TICKET_DTO);

        assertThat(actual).isEqualTo(TICKET);
    }
}