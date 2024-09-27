package com.ensah.examenplanapp.dao;

import com.ensah.examenplanapp.bo.Salle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ISalleRepositoryDao extends JpaRepository<Salle, Long> {
    // Vous pouvez définir des méthodes de requête personnalisées ici, si nécessaire
    Optional<Salle> findByNom(String nom);

}
