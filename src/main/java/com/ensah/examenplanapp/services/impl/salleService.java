package com.ensah.examenplanapp.services.impl;

import com.ensah.examenplanapp.bo.Salle;
import com.ensah.examenplanapp.dao.ISalleRepositoryDao;
import com.ensah.examenplanapp.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class salleService {

    private final ISalleRepositoryDao salleRepositoryDao;

    @Autowired
    public salleService(ISalleRepositoryDao salleRepositoryDao) {
        this.salleRepositoryDao = salleRepositoryDao;
    }

    public Optional<Salle> getSalleById(Long id) {
        return salleRepositoryDao.findById(id);
    }

    public Optional<Salle> getSalleByNom(String nom) {
        return salleRepositoryDao.findByNom(nom);
    }


    public List<Salle> getAllSalles() {
        return salleRepositoryDao.findAll();
    }

    public Salle saveSalle(Salle salle) {
        return salleRepositoryDao.save(salle);
    }

    public void deleteSalleById(Long id) {
        salleRepositoryDao.deleteById(id);
    }
    public void deleteSalleByNom(String nomSalle) {
        Optional<Salle> salleOptional = salleRepositoryDao.findByNom(nomSalle);
        if (salleOptional.isPresent()) {
            salleRepositoryDao.delete(salleOptional.get());
        } else {
            throw new EntityNotFoundException("Salle not found with name " + nomSalle);
        }
    }


    public Salle updateSalle(Long id, Salle salle) {
        Optional<Salle> existingSalleOptional = salleRepositoryDao.findById(id);
        if (existingSalleOptional.isPresent()) {
            Salle existingSalle = existingSalleOptional.get();
            // Mettre à jour les champs nécessaires
            existingSalle.setNom(salle.getNom());
            // Mettre à jour d'autres champs si nécessaire

            return salleRepositoryDao.save(existingSalle);
        } else {
            // Gérer le cas où la salle avec l'ID spécifié n'existe pas
            return null; // Ou lever une exception appropriée
        }
    }
}
