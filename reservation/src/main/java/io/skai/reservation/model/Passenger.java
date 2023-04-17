package io.skai.reservation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "passenger")
public class Passenger {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}