package com.ensah.examenplanapp.dao;

import com.ensah.examenplanapp.bo.Type_Examen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITypeExamenRepositoryDao extends JpaRepository<Type_Examen, Long> {
    // Vous pouvez définir des méthodes de requête personnalisées ici, si nécessaire
    Type_Examen findByType(String type);
}
