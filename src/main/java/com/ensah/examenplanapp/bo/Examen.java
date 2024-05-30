package com.ensah.examenplanapp.bo;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Examen implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date; // Représente une date au format "yyyy-MM-dd"

    private String heureDebut; // Représente une heure au format "HH:mm"

    private int dureePrev;

    private int dureeReelle;
    @Column(unique = true)
    private String epreuve;

    private String pv;

    @Column(columnDefinition = "TEXT")
    private String rapportTextuel = "Rien à signaler"; // Valeur par défaut

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    @ManyToOne
    @JoinColumn(name = "semestre_id")
    private Semestre semestre;

    @ManyToOne
    @JoinColumn(name = "type_examen_id")
    private Type_Examen typeExamen;

    @OneToMany(mappedBy = "examen",cascade = CascadeType.ALL)
    private List<surveillance> surveillances;

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    @ManyToOne
    @JoinColumn(name = "element_id")
    private Element element;


    // Autres attributs et méthodes

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public int getDureePrev() {
        return dureePrev;
    }

    public void setDureePrev(int dureePrev) {
        this.dureePrev = dureePrev;
    }

    public int getDureeReelle() {
        return dureeReelle;
    }

    public void setDureeReelle(int dureeReelle) {
        this.dureeReelle = dureeReelle;
    }

    public String getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(String epreuve) {
        this.epreuve = epreuve;
    }

    public String getPv() {
        return pv;
    }

    public void setPv(String pv) {
        this.pv = pv;
    }

    public String getRapportTextuel() {
        return rapportTextuel;
    }

    public void setRapportTextuel(String rapportTextuel) {
        this.rapportTextuel = rapportTextuel;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public Type_Examen getTypeExamen() {
        return typeExamen;
    }

    public void setTypeExamen(Type_Examen typeExamen) {
        this.typeExamen = typeExamen;
    }

    public List<surveillance> getSurveillances() {
        return surveillances;
    }

    public void setSurveillances(List<surveillance> surveillances) {
        this.surveillances = surveillances;
    }

    // Autres méthodes

}
