package com.ensah.examenplanapp.bo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

@Entity
public class Element implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long element_pedagogique_id; // Attribut id auto-incrémenté

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title cannot exceed 255 characters")
    @Column(unique = true)
    private String title;

    @OneToMany(mappedBy = "element",cascade = CascadeType.ALL)
    private List<Examen> exam ;


    @ManyToOne
    @JoinColumn(name = "coordonner_element_pedagogique_id")
    private Enseignant coordonnerElementPedagogique;

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    @ManyToOne
    @JoinColumn(name = "id_Niveau")
    private Niveau niveau;

    public Type_Element getType_element() {
        return type_element;
    }

    public void setType_element(Type_Element type_element) {
        this.type_element = type_element;
    }

    @ManyToOne
    @JoinColumn(name = "id_type_element")
    private Type_Element type_element;


    @ManyToOne
    @JoinColumn(name = "enseigner_element_pedagogique_id")
    private  Enseignant enseignerElementPedagogique;




    // Autres attributs et méthodes

    public Long getId() {
        return element_pedagogique_id;
    }

    public void setId(Long element_pedagogique_id) {
        this.element_pedagogique_id = element_pedagogique_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}

// Autres méthodes



