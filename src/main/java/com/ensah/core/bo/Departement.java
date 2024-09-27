package com.ensah.core.bo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@Entity
public class Departement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDepartement;
    private String title;


    @JsonIgnore
    @OneToMany(mappedBy = "departement",fetch = FetchType.LAZY)
    private Set<Professor> professors;
}
