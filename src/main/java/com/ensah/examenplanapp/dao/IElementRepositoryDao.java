package com.ensah.examenplanapp.dao;

import com.ensah.examenplanapp.bo.Element;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IElementRepositoryDao extends JpaRepository<Element, Long> {
    // Vous pouvez définir des méthodes de requête personnalisées ici, si nécessaire
    Element findByTitle(String title);
}
