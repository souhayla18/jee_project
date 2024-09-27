package com.ensah.examenplanapp.dao;

import com.ensah.examenplanapp.bo.surveillance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISurveillanceRepositoryDao extends JpaRepository<surveillance, Long> {
    // Vous pouvez définir des méthodes de requête personnalisées ici, si nécessaire
}
