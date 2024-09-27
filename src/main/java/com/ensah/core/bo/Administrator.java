package com.ensah.core.bo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.Set;


@Entity
@PrimaryKeyJoinColumn(name = "idAdministrator ")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Administrator extends Person{

    @NotNull
    @NotBlank(message = "This field should not be empty !")
    private String grade;


    @JsonIgnore
    @OneToMany(mappedBy = "administrator",fetch = FetchType.LAZY)
    private Set<Monitoring> monitorins;

}
