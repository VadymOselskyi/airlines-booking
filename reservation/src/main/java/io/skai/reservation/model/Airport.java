package io.skai.reservation.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Airport {

    private Long id;
    private String name;
    private String countryCode;
    private String city;
}