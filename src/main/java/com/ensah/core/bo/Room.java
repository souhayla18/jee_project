package com.ensah.core.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;


@Getter
@Setter
@Entity
public class  Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRoom;
    private String nameRoom;

    private String type;

    private String place;
    @Min(value = 10) @Max(value = 300)
    private Long size;

    @JsonIgnore
    @OneToMany(mappedBy = "room",fetch = FetchType.LAZY)
    private Set<Monitoring> monitorins;
}
