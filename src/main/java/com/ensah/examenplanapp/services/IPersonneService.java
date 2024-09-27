package com.ensah.examenplanapp.services;

import com.ensah.examenplanapp.bo.Personne;


import java.util.List;

public interface IPersonneService {

	public void addPersonne(Personne pPerson);

	public void updatePersonne(Personne pPerson);

	public List<Personne> getAllPersonnes();

	public void deletePersonne(Long id);

	public Personne getPersonneById(Long id);
	public void deleteByCinCascade(String cin);
	
	public Personne getPersonneByCin(String cin);

	
	

}
