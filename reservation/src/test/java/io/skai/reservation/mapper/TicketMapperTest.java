package io.skai.reservation.mapper;

import io.skai.reservation.dto.TicketDto;
import io.skai.reservation.jooq.tables.pojos.Ticket;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class TicketMapperTest {
    private final Ticket TICKET = new Ticket(1L, 1L, 1L, new BigDecimal(120), "1B");
    private final TicketDto TICKETDTO = new TicketDto(1L, 1L, 1L, new BigDecimal(120), "1B");

    private final TicketMapper ticketMapper = Mappers.getMapper(TicketMapper.class);

    @Test
    void whenInputTicketThenReturnTicketDto(){
        TicketDto actualDto = ticketMapper.ticketToTicketDto(TICKET);

        assertThat(actualDto).isEqualTo(TICKETDTO);
    }
    @Test
    void whenInputTicketDtoThenReturnTicket(){
        Ticket actual = ticketMapper.ticketDtoToTicket(TICKETDTO);

        assertThat(actual).isEqualTo(TICKET);
    }
}