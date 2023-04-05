package io.skai.historicaldata.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private Long id;
    private Long passengerId;
    private Long flightId;
    private BigDecimal cost;
}
