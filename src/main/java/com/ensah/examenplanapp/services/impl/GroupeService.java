package com.ensah.examenplanapp.services.impl;

import com.ensah.examenplanapp.bo.Groupe;
import com.ensah.examenplanapp.dao.IGroupeRepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupeService {

    private final IGroupeRepositoryDao groupeRepositoryDao;

    @Autowired
    public GroupeService(IGroupeRepositoryDao groupeRepositoryDao) {
        this.groupeRepositoryDao = groupeRepositoryDao;
    }

    public Optional<Groupe> getGroupeById(Long id) {
        return groupeRepositoryDao.findById(id);
    }

    public List<Groupe> getAllGroupes() {
        return groupeRepositoryDao.findAll();
    }

    public Optional<Groupe> getGroupeByNom(String nomGroupe) {
        return groupeRepositoryDao.findByNomGroupe(nomGroupe);
    }

    public Groupe saveGroupe(Groupe groupe) {
        return groupeRepositoryDao.save(groupe);
    }

    public void deleteGroupeById(Long id) {
        groupeRepositoryDao.deleteById(id);
    }

    public Groupe updateGroupe(Long id, Groupe groupe) {
        Optional<Groupe> existingGroupeOptional = groupeRepositoryDao.findById(id);
        if (existingGroupeOptional.isPresent()) {
            Groupe existingGroupe = existingGroupeOptional.get();
            // Mettre à jour les champs nécessaires
            existingGroupe.setNomGroupe(groupe.getNomGroupe());
            // Mettre à jour d'autres champs si nécessaire

            return groupeRepositoryDao.save(existingGroupe);
        } else {
            // Gérer le cas où le groupe avec l'ID spécifié n'existe pas
            return null; // Ou lever une exception appropriée
        }
    }
}
