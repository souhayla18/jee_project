package com.ensah.examenplanapp.bo;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
public class Departement implements Serializable {
    public Long getIdDepartement() {
        return idDepartement;
    }

    public void setIdDepartement(Long idDepartement) {
        this.idDepartement = idDepartement;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDepartement;

    public void setNomDep(String nomDep) {
        this.nomDep = nomDep;
    }
    @Column(unique = true)
    private String nomDep;
    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL)
    private Set<Enseignant> enseignant;


    public String getNom() {
        return nomDep;
    }
    public void setNom(String nom) {
        this.nomDep = nom;
    }





    @Override
    public String toString() {
        return "Departement{" +
                "idDepartement=" + idDepartement +
                ", nomDep='" + nomDep + '\'' +
                ", enseignant=" + enseignant +
                '}';
    }
}