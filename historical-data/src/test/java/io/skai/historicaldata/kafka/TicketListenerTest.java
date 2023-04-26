package io.skai.historicaldata.kafka;

import io.skai.historicaldata.dto.HistoricalTicketDto;
import io.skai.historicaldata.service.TicketService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

class TicketListenerTest {

    private static final HistoricalTicketDto TICKET_DTO = createHistoricalTicketDto();

    private final TicketService ticketService = mock(TicketService.class);
    private final TicketListener ticketListener = new TicketListener(ticketService);

    @Test
    void whenInputDtoIsValidTicketThenTicketServiceShouldSave() {
        when(ticketService.saveTicket(TICKET_DTO)).thenReturn(TICKET_DTO);

        ticketListener.listen(TICKET_DTO);
        verify(ticketService).saveTicket(TICKET_DTO);
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