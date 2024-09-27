package com.ensah.core.bo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
public class Educationalelement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idElement;
    private String title;

    //Element - Level
    @ManyToOne
    @JoinColumn(name = "id_level")
    private Level level;


    //modul/element
    @Enumerated(EnumType.STRING)
    @Column(name = "element_type")
    private ElementType elementType;



    //element - professor
    @ManyToOne
    @JoinColumn(name = "idprofessor")
    private Professor professor;


    //surveillance - coordonator
    @ManyToOne
    @JoinColumn(name = "idcoordinator")
    private Professor coordinator;


    //element - exam
    @JsonIgnore
    @OneToMany(mappedBy = "element",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Exam> exams;


}
