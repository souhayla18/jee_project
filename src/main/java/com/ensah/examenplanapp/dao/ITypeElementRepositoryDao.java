package com.ensah.examenplanapp.dao;

import com.ensah.examenplanapp.bo.Type_Element;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITypeElementRepositoryDao extends JpaRepository<Type_Element, Long> {
    // Vous pouvez définir des méthodes de requête personnalisées ici, si nécessaire
    Type_Element findByTypeElement(String typeElement);

}
