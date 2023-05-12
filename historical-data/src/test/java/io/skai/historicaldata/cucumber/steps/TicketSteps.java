package io.skai.historicaldata.cucumber.steps;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.skai.historicaldata.cucumber.SpringIntegrationTest;
import io.skai.historicaldata.dto.HistoricalTicketDto;
import org.assertj.core.api.Assertions;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class TicketSteps extends SpringIntegrationTest {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Given("create tickets")
    public void sendTicketsToTopic(List<HistoricalTicketDto> dtos) {
        dtos.forEach(dto -> kafkaTemplate.send(kafkaProperties.getTopic(), dto));
    }

    @When("wait {int} seconds")
    public void waiting(int pause) throws InterruptedException {
        Thread.sleep(pause * 1000L);
    }

    @Then("client can get all Tickets by email: {string}")
    public void verifyReceivedTickets(String email, List<HistoricalTicketDto> airportDtos) {
        List<HistoricalTicketDto> historyTickets = ticketClient.getHistoryTickets(email);
        Assertions.assertThat(historyTickets)
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
                .isEqualTo(airportDtos);
    }

    @DataTableType(replaceWithEmptyString = "[null]")
    public HistoricalTicketDto parseToHistoricalTicketDto(Map<String, String> row) {
        return new HistoricalTicketDto(
                verifyId(row),
                row.get("firstName"),
                row.get("lastName"),
                row.get("email"),
                row.get("phone"),
                row.get("seatNumber"),
                new BigDecimal(row.get("price")),
                LocalDateTime.parse(row.get("departureDate"), DATE_TIME_FORMATTER),
                row.get("departureAirportName"),
                row.get("departureCountryCode"),
                row.get("departureCity"),
                LocalDateTime.parse(row.get("arrivalDate"), DATE_TIME_FORMATTER),
                row.get("arrivalAirportName"),
                row.get("arrivalCountryCode"),
                row.get("arrivalCity")
        );
    }

    private static Long verifyId(Map<String, String> row) {
        return row.get("id") != null ? Long.parseLong(row.get("id")) : null;
    }
}