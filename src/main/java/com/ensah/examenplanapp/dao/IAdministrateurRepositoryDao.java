package com.ensah.examenplanapp.dao;

import com.ensah.examenplanapp.bo.CadreAdministrateur;
import com.ensah.examenplanapp.bo.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAdministrateurRepositoryDao extends JpaRepository<CadreAdministrateur, Long> {
    // Vous pouvez définir des méthodes de requête personnalisées ici, si nécessaire
    void deleteByCin(String cin);
    Optional<CadreAdministrateur> findByCin(String cin);
    Optional<CadreAdministrateur> findByGradeAdmin(String gradeAdmin);

}
