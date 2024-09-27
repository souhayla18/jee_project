package com.ensah.examenplanapp.dao;

import com.ensah.examenplanapp.bo.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISessionRepositoryDao extends JpaRepository<Session, Long> {
    // Vous pouvez définir des méthodes de requête personnalisées ici, si nécessaire
    Session findByName(String name);
}
