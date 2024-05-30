package com.ensah.examenplanapp.dao;

import com.ensah.examenplanapp.bo.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonneRepository extends JpaRepository<Personne, Long> {

	// Méthode personnalisée pour trouver une personne par son CIN
	Personne getPersonneByCin(String cin);


	// Méthode personnalisée pour trouver une personne par son nom et son prénom
	Personne findByNomAndPrenom(String nom, String prenom);
}
