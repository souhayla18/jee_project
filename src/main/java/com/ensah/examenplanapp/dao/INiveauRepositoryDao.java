package com.ensah.examenplanapp.dao;

import com.ensah.examenplanapp.bo.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INiveauRepositoryDao extends JpaRepository<Niveau, Long> {
    // Méthode personnalisée pour trouver un niveau par son nom
    Niveau findByName(String name);
}
