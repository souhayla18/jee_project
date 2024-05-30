package com.ensah.examenplanapp.dao;

import com.ensah.examenplanapp.bo.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICompteRepository extends JpaRepository<Compte, Long> {
	public Compte getCompteByLogin(String username);

}
