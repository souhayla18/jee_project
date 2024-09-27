package com.ensah.examenplanapp.dao;

import com.ensah.examenplanapp.bo.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IGroupeRepositoryDao extends JpaRepository<Groupe, Long> {
    Optional<Groupe> findByNomGroupe(String nomGroupe);
}
