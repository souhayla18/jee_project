package com.ensah.core.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExam;
    private String startTime;
    private String endTime;
    private String duration;
    private String reelDuration;
    private String preuve;
    private String pv;
    private String Rapport;


    //monitoring-exam
    @JsonIgnore
    @OneToMany(mappedBy = "exam",fetch = FetchType.LAZY)
    private Set<Monitoring> monitorins;


    //element - exam
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_element")
    private Educationalelement element;


    //session-exam
    @Enumerated(EnumType.STRING)
    private Session session;


    //semester-exam
    @Enumerated(EnumType.STRING)
    private Semester semester;


    //examtype-exam
    @Enumerated(EnumType.STRING)
    private ExamType examType;
}
