package com.ensah.examenplanapp.bo;


import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.util.Date;


@Entity
@PrimaryKeyJoinColumn(name = "idEtudiant")
public class Etudiant extends Personne {

	private String cne;

	private Date dateNaissance;



	public String getCne() {
		return cne;
	}

	public void setCne(String cne) {
		this.cne = cne;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}




}