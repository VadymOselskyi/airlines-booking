package io.skai.historicaldata;

import io.skai.historicaldata.kafka.TicketListener;
import io.skai.historicaldata.repository.TicketRepository;
import io.skai.historicaldata.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class BaseApplicationContextTest {

    @Autowired
    protected TicketRepository ticketRepository;

    @Autowired
    protected TicketService ticketService;

    @Autowired
    protected TicketListener ticketListener;
}