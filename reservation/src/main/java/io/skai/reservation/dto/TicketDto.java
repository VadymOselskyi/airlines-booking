package io.skai.reservation.dto;

import java.math.BigDecimal;

public record TicketDto(Long id,
                        Long passengerId,
                        Long flightId,
                        BigDecimal price) {
}