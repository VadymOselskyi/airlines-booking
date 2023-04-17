package io.skai.historicaldata.repository;

import io.skai.historicaldata.model.HistoricalTicket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

@SpringBootTest
@ActiveProfiles("test")
class TicketRepositoryTest {

    private final HistoricalTicket TICKET =
            new HistoricalTicket(1L, "FName", "LName", "email@gmail.com",
                    "123", "B12", new BigDecimal("123.00"),
                    LocalDateTime.of(2023, 4, 17, 14, 20),
                    "arrAir", "UA", "Kyiv",
                    LocalDateTime.of(2023, 4, 17, 21, 35),
                    "arrAir", "GB", "London");

    @Autowired
    private TicketRepository ticketRepository;

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
}