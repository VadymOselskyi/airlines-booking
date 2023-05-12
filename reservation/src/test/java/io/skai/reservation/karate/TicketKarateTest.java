package io.skai.reservation.karate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.intuit.karate.junit5.Karate;
import io.skai.reservation.dto.HistoryTicketDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class TicketKarateTest extends SpringKarateTest {

    private static final int PORT_NUMBER = 8081;

    private static final WireMockServer wireMockServer = new WireMockServer(WireMockConfiguration.options().port(PORT_NUMBER));

    private final static HistoryTicketDto TICKET_DTO = createHistoryTicketDto();

    @BeforeAll
    public static void prepareWiremock() {
        wireMockServer.start();
        configureFor("localhost", PORT_NUMBER);
        stubFor(get(urlEqualTo("/api/tickets?email=johnsmith%40gmail.com"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(convertToJson(List.of(TICKET_DTO)))));
    }

    @Karate.Test
    public Karate karateTest() {
        return Karate.run("ticket").relativeTo(getClass());
    }

    @AfterAll
    public static void shotDownWireMock() {
        wireMockServer.stop();
    }

    private static HistoryTicketDto createHistoryTicketDto() {
        return new HistoryTicketDto("Tony", "Stark", "ironman@gmail.com", "380942847", "B12", new BigDecimal(120),
                LocalDateTime.of(2023, 4, 11, 5, 40), "NEW YORK INTERNATIONAL", "US", "New York",
                LocalDateTime.of(2023, 4, 12, 8, 30), "LVIV INTERNATIONAL", "UA", "Lviv");
    }

    private static String convertToJson(List<HistoryTicketDto> tickets) {
        JsonMapper jsonMapper = new JsonMapper();
        jsonMapper.registerModule(new JavaTimeModule());
        try {
            return jsonMapper.writeValueAsString(tickets);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}