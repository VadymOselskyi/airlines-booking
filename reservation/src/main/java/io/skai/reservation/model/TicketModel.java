package io.skai.reservation.model;

import lombok.*;

import java.math.BigDecimal;

//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@EqualsAndHashCode
//@ToString
public record TicketModel(Long id, Long passengerId, Long flightId, BigDecimal price) {

}
