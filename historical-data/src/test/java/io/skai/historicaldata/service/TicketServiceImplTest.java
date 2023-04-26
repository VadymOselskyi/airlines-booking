package io.skai.historicaldata.service;

import io.skai.historicaldata.dto.HistoricalTicketDto;
import io.skai.historicaldata.exception.TicketWasNotSavedException;
import io.skai.historicaldata.mapper.TicketMapper;
import io.skai.historicaldata.model.HistoricalTicket;
import io.skai.historicaldata.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

class TicketServiceImplTest {

    private final HistoricalTicket TICKET =
            new HistoricalTicket(1L, "FName", "LName", "email@gmail.com",
                    "123", "B12", new BigDecimal("123.00"),
                    LocalDateTime.of(2023, 4, 17, 14, 20),
                    "arrAir", "UA", "Kyiv",
                    LocalDateTime.of(2023, 4, 17, 21, 35),
                    "arrAir", "GB", "London");
    private final HistoricalTicketDto TICKETDTO =
            new HistoricalTicketDto(1L, "FName", "LName", "email@gmail.com",
                    "123", "B12", new BigDecimal("123.00"),
                    LocalDateTime.of(2023, 4, 17, 14, 20),
                    "arrAir", "UA", "Kyiv",
                    LocalDateTime.of(2023, 4, 17, 21, 35),
                    "arrAir", "GB", "London");

    private final TicketRepository ticketRepository = Mockito.mock(TicketRepository.class);
    private final TicketMapper ticketMapper = Mockito.mock(TicketMapper.class);

    private final TicketServiceImpl ticketService = new TicketServiceImpl(ticketRepository, ticketMapper);

    @Test
    void whenDbHasTicketsWithTheSameEmailThenGetTicketsReturnTheir() {

        when(ticketMapper.historicalTicketToHistoricalTicketDto(TICKET)).thenReturn(TICKETDTO);
        when(ticketRepository.findAll(TICKET.getEmail())).thenReturn(List.of(TICKET));

        List<HistoricalTicketDto> tickets = ticketService.getTickets(TICKET.getEmail());

        assertThat(tickets)
                .hasSize(1)
                .contains(TICKETDTO);

        verify(ticketRepository).findAll(TICKET.getEmail());
        verify(ticketMapper).historicalTicketToHistoricalTicketDto(TICKET);
    }

    @Test
    void whenCreateOneTicketThenTicketRepositoryShouldInsertOnce() {

        when(ticketMapper.historicalTicketDtoToHistoricalTicket(TICKETDTO)).thenReturn(TICKET);

        ticketService.saveTicket(TICKETDTO);

        verify(ticketRepository).save(TICKET);
        verify(ticketMapper).historicalTicketDtoToHistoricalTicket(TICKETDTO);
    }

    @Test
    void whenInputDtoIsNullThenThrowException() {
        when(ticketRepository.save(null)).thenThrow(new IllegalArgumentException());

        assertThatThrownBy(() -> ticketService.saveTicket(null))
                .isInstanceOf(TicketWasNotSavedException.class)
                .hasMessage("Ticket is null or uses optimistic locking");
    }
}