package com.ensah.examenplanapp.dao;

import com.ensah.examenplanapp.bo.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISemestreRepositoryDao extends JpaRepository<Semestre, Long> {
    // Vous pouvez définir des méthodes de requête personnalisées ici, si nécessaire
    Semestre findByName(String name);
}
