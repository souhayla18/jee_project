package com.ensah.examenplanapp.bo;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Salle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int capacite;
    @Column(unique = true)
    private String nom ;
    @OneToMany(mappedBy = "salle",cascade = CascadeType.ALL)
    private List<surveillance> surveillances;

    // Autres attributs et méthodes

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public List<surveillance> getSurveillances() {
        return surveillances;
    }

    public void setSurveillances(List<surveillance> surveillances) {
        this.surveillances = surveillances;
    }

    // Autres méthodes
    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public int getCapacite() {
        return capacite;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom_salle) {
        this.nom = nom_salle;
    }

}

