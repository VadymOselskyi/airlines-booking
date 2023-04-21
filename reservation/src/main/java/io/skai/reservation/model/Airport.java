package io.skai.reservation.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Airport {

    private Long id;
    private String name;
    private String countryCode;
    private String city;
}