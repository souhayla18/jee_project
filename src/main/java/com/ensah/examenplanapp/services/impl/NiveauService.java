package com.ensah.examenplanapp.services.impl;

import com.ensah.examenplanapp.bo.Niveau;
import com.ensah.examenplanapp.dao.INiveauRepositoryDao;
import com.ensah.examenplanapp.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NiveauService {

    private final INiveauRepositoryDao niveauRepositoryDao;

    @Autowired
    public NiveauService(INiveauRepositoryDao niveauRepositoryDao) {
        this.niveauRepositoryDao = niveauRepositoryDao;
    }

    public Optional<Niveau> getNiveauById(Long id) {
        return niveauRepositoryDao.findById(id);
    }

    public Optional<Niveau> getNiveauByName(String name) {
        return Optional.ofNullable(niveauRepositoryDao.findByName(name));
    }

    public List<Niveau> getAllNiveaux() {
        return niveauRepositoryDao.findAll();
    }

    public Niveau saveNiveau(Niveau niveau) {
        return niveauRepositoryDao.save(niveau);
    }

    public void deleteNiveauById(Long id) {
        niveauRepositoryDao.deleteById(id);
    }
    public void deleteNiveauByNom(String name) {
        Niveau niveau = niveauRepositoryDao.findByName(name);
        if (niveau != null) {
            niveauRepositoryDao.delete(niveau);
        } else {
            throw new EntityNotFoundException("Niveau not found with name " + name);
        }
    }

    public Niveau updateNiveau(Long id, Niveau niveau) {
        Optional<Niveau> existingNiveauOptional = niveauRepositoryDao.findById(id);
        if (existingNiveauOptional.isPresent()) {
            Niveau existingNiveau = existingNiveauOptional.get();
            // Mettre à jour les champs nécessaires
            existingNiveau.setName(niveau.getName());
            // Mettre à jour d'autres champs si nécessaire

            return niveauRepositoryDao.save(existingNiveau);
        } else {
            // Gérer le cas où le niveau avec l'ID spécifié n'existe pas
            return null; // Ou lever une exception appropriée
        }
    }
}
