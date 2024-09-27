package com.ensah.core.bo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Entity
@Getter
@Setter
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLevel;
    private String title;


    @JsonIgnore
    @OneToMany(mappedBy = "level",fetch = FetchType.LAZY)
    private Set<Educationalelement> elemnts;
}
