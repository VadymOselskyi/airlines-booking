package io.skai.historicaldata.repository;

import io.skai.historicaldata.BaseApplicationContextTest;
import io.skai.historicaldata.model.HistoricalTicket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

class TicketRepositoryTest extends BaseApplicationContextTest {

    private static final HistoricalTicket TICKET = createHistoricalTicket();

    @AfterEach
    void tearDown() {
        ticketRepository.deleteAll();
    }

    @Test
    void whenFindAllThenMethodReturnTicketsWithRightEmail() {
        HistoricalTicket savedTicket = ticketRepository.save(TICKET);
        List<HistoricalTicket> historicalTickets = ticketRepository.findAll(TICKET.getEmail());

        assertThat(historicalTickets, contains(savedTicket));
    }

    private static HistoricalTicket createHistoricalTicket() {
        return new HistoricalTicket(null, "FName", "LName", "email@gmail.com",
                "123", "B12", new BigDecimal("123.00"),
                LocalDateTime.of(2023, 4, 17, 14, 20),
                "arrAir", "UA", "Kyiv",
                LocalDateTime.of(2023, 4, 17, 21, 35),
                "arrAir", "GB", "London");
    }
}