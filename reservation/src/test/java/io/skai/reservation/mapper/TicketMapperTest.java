package io.skai.reservation.mapper;

import io.skai.reservation.dto.TicketDto;
import io.skai.reservation.jooq.tables.pojos.Ticket;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class TicketMapperTest {
    private final Ticket ticket = new Ticket(4L, 15L, 81L, new BigDecimal(120), "51B");
    private final TicketDto ticketDto = new TicketDto(4L, 15L, 81L, new BigDecimal(120), "51B");

    private final TicketMapper ticketMapper = Mappers.getMapper(TicketMapper.class);

    @Test
    void whenInputTicketThenReturnTicketDto(){
        TicketDto actualDto = ticketMapper.ticketToTicketDto(ticket);

        assertThat(actualDto).isEqualTo(ticketDto);
    }

    @Test
    void whenInputTicketDtoThenReturnTicket(){
        Ticket actual = ticketMapper.ticketDtoToTicket(ticketDto);

        assertThat(actual).isEqualTo(ticket);
    }
}