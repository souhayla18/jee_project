package com.ensah.core.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PersonDTO {

    private Long idPerson;

    @NotNull @NotBlank(message = "This field should not be empty!")
    private String firstName;

    @NotNull @NotBlank(message = "This field should not be empty!")
    private String lastName;

    @NotNull @NotBlank(message = "This field should not be empty!")
    private String type;

    @NotNull @NotBlank(message = "This field should not be empty!")
    private String cin;

    @NotNull @NotBlank(message = "This field should not be empty!")
    private String email;


    // Fields specific to Administrator
    private String grade;

    // Fields specific to Professor
    private String speciality;
    private Long idDepartement;
    private Long idSector;
}
