package com.ensah.examenplanapp.bo;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Filiere implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int filiere_id;
    @Column(unique = true)
    private String nom;
    private int nombreEtudiants;

    @OneToMany(mappedBy="filiere",cascade = CascadeType.ALL)
    private List<Enseignant> enseignants;

    // Getters et setters pour les attributs de la filière
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNombreEtudiants() {
        return nombreEtudiants;
    }

    public void setNombreEtudiants(int nombreEtudiants) {
        this.nombreEtudiants = nombreEtudiants;
    }



    // Getters et setters pour la collection d'enseignants
    public List<Enseignant> getEnseignants() {
        return enseignants;
    }

    public void setEnseignants(List<Enseignant> enseignants) {
        this.enseignants = enseignants;
    }

    public int getFiliere_id() {
        return filiere_id;
    }

    public void setFiliere_id(int filiere_id) {
        this.filiere_id = filiere_id;
    }
}

