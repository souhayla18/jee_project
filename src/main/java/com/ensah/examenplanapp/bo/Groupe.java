package com.ensah.examenplanapp.bo;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
public class Groupe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nomGroupe;

    public Set<Enseignant> getEnseignants() {
        return enseignants;
    }

    public void setEnseignants(Set<Enseignant> enseignants) {
        this.enseignants = enseignants;
    }
//, cascade = CascadeType.ALL ( a verifier )
    @OneToMany(mappedBy = "groupe")
    private Set<Enseignant> enseignants ;




    // Constructeurs, getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomGroupe() {
        return nomGroupe;
    }

    public void setNomGroupe(String nomGroupe) {
        this.nomGroupe = nomGroupe;
    }


}


