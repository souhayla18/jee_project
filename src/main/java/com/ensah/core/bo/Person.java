package com.ensah.core.bo;


import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPerson;

    private String firstName;

    private String lastName;

    private String type;

    private String cin;

    @Override
    public String toString() {
        return "Person [idPerson=" + idPerson + ", Firstname=" + firstName + ", Lastname=" + lastName + ", cin=" + cin + "]";
    }
}
