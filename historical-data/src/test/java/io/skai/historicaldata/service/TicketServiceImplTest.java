package io.skai.historicaldata.service;

import io.skai.historicaldata.dto.HistoricalTicketDto;
import io.skai.historicaldata.mapper.TicketMapper;
import io.skai.historicaldata.model.HistoricalTicket;
import io.skai.historicaldata.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TicketServiceImplTest {

    private static final HistoricalTicket TICKET = createHistoricalTicket();
    private static final HistoricalTicketDto TICKET_DTO = createHistoricalTicketDto();

    private final TicketRepository ticketRepository = Mockito.mock(TicketRepository.class);
    private final TicketMapper ticketMapper = Mockito.mock(TicketMapper.class);
    private final TicketServiceImpl ticketService = new TicketServiceImpl(ticketRepository, ticketMapper);

    @Test
    void whenDbHasTicketsWithTheSameEmailThenGetTicketsReturnTheir() {
        when(ticketMapper.historicalTicketToHistoricalTicketDto(TICKET)).thenReturn(TICKET_DTO);
        when(ticketRepository.findAll(TICKET.getEmail())).thenReturn(List.of(TICKET));

        List<HistoricalTicketDto> tickets = ticketService.getTickets(TICKET.getEmail());

        assertThat(tickets).containsExactly(TICKET_DTO);
        verify(ticketRepository).findAll(TICKET.getEmail());
        verify(ticketMapper).historicalTicketToHistoricalTicketDto(TICKET);
    }

    @Test
    void whenCreateOneTicketThenTicketRepositoryShouldInsertOnce() {
        when(ticketMapper.historicalTicketDtoToHistoricalTicket(TICKET_DTO)).thenReturn(TICKET);

        ticketService.saveTicket(TICKET_DTO);

        verify(ticketRepository).save(TICKET);
        verify(ticketMapper).historicalTicketDtoToHistoricalTicket(TICKET_DTO);
    }

    private static HistoricalTicket createHistoricalTicket() {
        return new HistoricalTicket(1L, "FName", "LName", "email@gmail.com",
                "123", "B12", new BigDecimal("123.00"),
                LocalDateTime.of(2023, 4, 17, 14, 20),
                "arrAir", "UA", "Kyiv",
                LocalDateTime.of(2023, 4, 17, 21, 35),
                "arrAir", "GB", "London");
    }

    private static HistoricalTicketDto createHistoricalTicketDto() {
        return new HistoricalTicketDto(1L, "FName", "LName", "email@gmail.com",
                "123", "B12", new BigDecimal("123.00"),
                LocalDateTime.of(2023, 4, 17, 14, 20),
                "arrAir", "UA", "Kyiv",
                LocalDateTime.of(2023, 4, 17, 21, 35),
                "arrAir", "GB", "London");
    }
}