package com.ensah.examenplanapp.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class surveillance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surveillances_id;



    private int nombreSurveillant = 2;
    // Valeur par défaut


    @ManyToOne
    @JoinColumn(name = "salle_id")
    private Salle salle;
    @ManyToMany(mappedBy="surveillancesSurveillees")
//sans faire jsonignore car on ai dans la serialization binaire et non pas json
    private  transient List<Enseignant> surveillancesSurveillees;


    @ManyToOne
    @JoinColumn(name = "Coordonnes_enseignant")
    private Enseignant surveillancesCoordonnes;

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    @ManyToOne
    @JoinColumn(name = "examen_id")
    private Examen examen;


    public CadreAdministrateur getAdmin() {
        return admin;
    }

    public void setAdmin(CadreAdministrateur admin) {
        this.admin = admin;
    }

    @ManyToOne
    @JoinColumn(name = "Admin_id")
    @JsonIgnore
    private CadreAdministrateur admin;


    //enseignantsCoordonnes

    // Autres attributs et méthodes

    public Long getId() {
        return surveillances_id;
    }

    public void setId(Long surveillances_id) {
        this.surveillances_id = surveillances_id;
    }

    public int getNombreSurveillant() {
        return nombreSurveillant;
    }

    public void setNombreSurveillant(int nombreSurveillant) {
        this.nombreSurveillant = nombreSurveillant;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    // Autres méthodes

}

