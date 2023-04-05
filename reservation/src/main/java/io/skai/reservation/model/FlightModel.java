package io.skai.reservation.model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class FlightModel {

    private Long id;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private Long departureAirportId;
    private Long arrivalAirportId;
}
