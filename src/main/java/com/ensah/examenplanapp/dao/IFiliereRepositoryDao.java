package com.ensah.examenplanapp.dao;

import com.ensah.examenplanapp.bo.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFiliereRepositoryDao extends JpaRepository<Filiere, Integer> {
    // Méthode personnalisée pour trouver une filière par son nom
    Filiere findByNom(String nom);
}
