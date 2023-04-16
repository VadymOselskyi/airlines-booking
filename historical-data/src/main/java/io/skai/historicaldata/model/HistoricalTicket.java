package io.skai.historicaldata.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "history_ticket")
public class HistoricalTicket {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String seatNumber;
    private BigDecimal price;
    private LocalDateTime departureDate;
    private String departureAirportName;
    private String departureCountryCode;
    private String departureCity;
    private LocalDateTime arrivalDate;
    private String arrivalAirportName;
    private String arrivalCountryCode;
    private String arrivalCity;
}