package io.skai.reservation.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PassengerModel {
    Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
