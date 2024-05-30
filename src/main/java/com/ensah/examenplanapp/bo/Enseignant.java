package com.ensah.examenplanapp.bo;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;


@Entity
@PrimaryKeyJoinColumn(name = "idEnseignant")
public class Enseignant extends Personne implements Serializable {

	//doctorabt ou vacataire
	private String grade;
	private int heuresEnseigneesParSemaine;
	private String bureau;




	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	public Filiere getFiliere() {
		return filiere;
	}

	@ManyToOne
	@JoinColumn(name = "filiere_id") // Correspond au nom de la colonne dans la table Enseignant
	private Filiere filiere;

	@ManyToOne
	@JoinColumn(name = "departement_id") // Correspond au nom de la colonne dans la table Enseignant
	private Departement department;

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	@ManyToOne
	@JoinColumn(name = "groupe_id")
	private Groupe groupe;

//(cascade=CascadeType.ALL)
	@OneToMany
	@JoinColumn(name = "surveillances_id")
	private List<surveillance> surveillancesCoordonnees;


	@ManyToMany
	@JoinTable(name = "surveillance_enseignant",
			joinColumns = @JoinColumn(name = "surveillances_id"),
			inverseJoinColumns = @JoinColumn(name = "idEnseignant"))
	private List<surveillance> surveillancesSurveillees;
//cascade = CascadeType.ALL
	@OneToMany( mappedBy = "coordonnerElementPedagogique")
	private List<Element> elementsPedagogiquesCoordonnes;

//cascade = CascadeType.ALL
	@OneToMany( mappedBy = "enseignerElementPedagogique")
	private List<Element> elementsPedagogiquesEnseigner;








	public int getHeuresEnseigneesParSemaine() {
		return heuresEnseigneesParSemaine;
	}

	public void setHeuresEnseigneesParSemaine(int heuresEnseigneesParSemaine) {
		this.heuresEnseigneesParSemaine = heuresEnseigneesParSemaine;
	}

	public String getBureau() {
		return bureau;
	}

	public void setBureau(String bureau) {
		this.bureau = bureau;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Departement getDepartement() {
		return department;
	}

	public void setDepartement(Departement departement) {
		this.department = departement;
	}
}
