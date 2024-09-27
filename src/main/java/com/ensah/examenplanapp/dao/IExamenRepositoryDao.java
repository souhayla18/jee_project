package com.ensah.examenplanapp.dao;

import com.ensah.examenplanapp.bo.Examen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IExamenRepositoryDao extends JpaRepository<Examen, Long> {
    // Méthode personnalisée pour trouver un examen par son épreuve
    Optional<Examen> findByEpreuve(String epreuve);
}
