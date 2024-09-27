package com.ensah.core.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Monitoring {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMonitor;
    private String dateExam;


    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "Monitors_Profs",
            joinColumns = @JoinColumn(name = "id_Monitoring"),
            inverseJoinColumns = @JoinColumn(name = "id_Professor"))
    @JsonIgnore
    private Set<Professor> professors=new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Cordinator")
    @JsonIgnoreProperties({"monitorins", "hibernateLazyInitializer", "handler"})
    private Professor coordinator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Administator")
    @JsonIgnoreProperties({"monitorins", "hibernateLazyInitializer", "handler"})
    private Administrator administrator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"monitorins", "hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "id_Room")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"monitorins", "hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "id_Exam")
    private Exam exam;
}
