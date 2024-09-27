package com.ensah.examenplanapp.dao;

import com.ensah.examenplanapp.bo.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDepartementRepositoryDao extends JpaRepository<Departement, Long> {
    // Méthode personnalisée pour trouver un département par son nom
    Departement findByNomDep(String nomDep);
}
