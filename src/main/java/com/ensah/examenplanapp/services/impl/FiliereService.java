package com.ensah.examenplanapp.services.impl;

import com.ensah.examenplanapp.bo.Filiere;
import com.ensah.examenplanapp.dao.IFiliereRepositoryDao;
import com.ensah.examenplanapp.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FiliereService {

    private final IFiliereRepositoryDao filiereRepositoryDao;

    @Autowired
    public FiliereService(IFiliereRepositoryDao filiereRepositoryDao) {
        this.filiereRepositoryDao = filiereRepositoryDao;
    }

    public Optional<Filiere> getFiliereById(Integer id) {
        return filiereRepositoryDao.findById(id);
    }

    public Optional<Filiere> getFiliereByNom(String nom) {
        return Optional.ofNullable(filiereRepositoryDao.findByNom(nom));
    }

    public List<Filiere> getAllFilieres() {
        return filiereRepositoryDao.findAll();
    }

    public Filiere saveFiliere(Filiere filiere) {
        return filiereRepositoryDao.save(filiere);
    }

    public void deleteFiliereById(Integer id) {
        filiereRepositoryDao.deleteById(id);
    }
    public void deleteFiliereByNom(String nom) {
        Filiere filiere = filiereRepositoryDao.findByNom(nom);
        if (filiere != null) {
            filiereRepositoryDao.delete(filiere);
        } else {
            throw new EntityNotFoundException("Filiere not found with name " + nom);
        }
    }
    public Filiere updateFiliere(Filiere updatedFiliere) {
        Optional<Filiere> existingFiliereOptional = filiereRepositoryDao.findById(updatedFiliere.getFiliere_id());
        if (existingFiliereOptional.isPresent()) {
            Filiere existingFiliere = existingFiliereOptional.get();
            // Mise à jour des champs de la filière existante avec les valeurs de la filière mise à jour
            existingFiliere.setNom(updatedFiliere.getNom());
            // Mettre à jour d'autres champs si nécessaire
            return filiereRepositoryDao.save(existingFiliere);
        } else {
            throw new EntityNotFoundException("Filiere not found with id " + updatedFiliere.getFiliere_id());
        }
    }

    // Ajouter d'autres méthodes de service si nécessaire
}
