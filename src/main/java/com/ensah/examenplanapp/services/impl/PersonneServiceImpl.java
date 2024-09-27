package com.ensah.examenplanapp.services.impl;

import com.ensah.examenplanapp.bo.CadreAdministrateur;
import com.ensah.examenplanapp.bo.Enseignant;
import com.ensah.examenplanapp.bo.Personne;
import com.ensah.examenplanapp.dao.IAdministrateurRepositoryDao;
import com.ensah.examenplanapp.dao.IEnseignantRepositoryDao;
import com.ensah.examenplanapp.dao.IPersonneRepository;
import com.ensah.examenplanapp.services.IPersonneService;
import com.ensah.examenplanapp.services.exceptions.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonneServiceImpl implements IPersonneService {

	@Autowired
	private IPersonneRepository personDao;

	@Autowired
	private IAdministrateurRepositoryDao administrateurRepository;

	@Autowired
	private IEnseignantRepositoryDao enseignantRepository;

	@Override
	public List<Personne> getAllPersonnes() {
		return personDao.findAll();
	}

	@Override
	public void deletePersonne(Long id) {
		personDao.deleteById(id);
	}

	@Override
	public void deleteByCinCascade(String cin) {
		// Récupérer la personne à supprimer par son CIN
		Personne personne = personDao.getPersonneByCin(cin);
		if (personne != null) {
			// Supprimer les entités enfants associées (Administrateur, Enseignant, etc.)
			administrateurRepository.deleteById(personne.getIdPersonne());
			enseignantRepository.deleteById(personne.getIdPersonne());

			// Supprimer la personne elle-même
			personDao.delete(personne);
		} else {
			throw new EntityNotFoundException("Personne not found with CIN " + cin);
		}
	}

	@Override
	public Personne getPersonneById(Long id) {
		Optional<Personne> optionalPersonne = personDao.findById(id);
		return optionalPersonne.orElseThrow(() -> new EntityNotFoundException("Personne not found with ID " + id));
	}


	@Override
	public void addPersonne(Personne pPerson) {
		if (pPerson instanceof CadreAdministrateur) {
			administrateurRepository.save((CadreAdministrateur) pPerson);
		} else if (pPerson instanceof Enseignant) {
			enseignantRepository.save((Enseignant) pPerson);
		} else {
			// Traitez le cas où la personne ne correspond ni à un CadreAdministrateur ni à un Enseignant
			throw new IllegalArgumentException("La personne doit être soit un CadreAdministrateur soit un Enseignant");
		}
	}



	@Override
	public void updatePersonne(Personne pPerson) {
		// Vérifier si la personne est un administrateur
		if (pPerson instanceof CadreAdministrateur) {
			CadreAdministrateur administrateur = (CadreAdministrateur) pPerson;
			// Mettre à jour l'administrateur correspondant dans la base de données
			administrateurRepository.save(administrateur);
		} else if (pPerson instanceof Enseignant) {
			Enseignant enseignant = (Enseignant) pPerson;
			// Mettre à jour l'enseignant correspondant dans la base de données
			enseignantRepository.save(enseignant);
		}
	}


	@Override
	public Personne getPersonneByCin(String cin) {
		return personDao.getPersonneByCin(cin);
	}
}
