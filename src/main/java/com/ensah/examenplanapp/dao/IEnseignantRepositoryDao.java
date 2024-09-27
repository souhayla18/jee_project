package com.ensah.examenplanapp.dao;

import com.ensah.examenplanapp.bo.Enseignant;
import com.ensah.examenplanapp.bo.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEnseignantRepositoryDao extends JpaRepository<Enseignant, Long> {
    List<Enseignant> findByGrade(String grade);
    Enseignant findByGradeAndHeuresEnseigneesParSemaineAndBureau(String grade, int heuresEnseigneesParSemaine, String bureau);
}
