package com.ensah.examenplanapp.services.impl;

import com.ensah.examenplanapp.bo.Departement;
import com.ensah.examenplanapp.bo.Enseignant;
import com.ensah.examenplanapp.bo.Filiere;
import com.ensah.examenplanapp.bo.Groupe;
import com.ensah.examenplanapp.dao.IDepartementRepositoryDao;
import com.ensah.examenplanapp.dao.IEnseignantRepositoryDao;
import com.ensah.examenplanapp.dao.IFiliereRepositoryDao;
import com.ensah.examenplanapp.dao.IGroupeRepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EnseignantService {

    @Autowired
    private IEnseignantRepositoryDao enseignantRepositoryDao;

    @Autowired
    private IDepartementRepositoryDao departementRepositoryDao;

    @Autowired
    private IFiliereRepositoryDao filiereRepositoryDao;

    @Autowired
    private IGroupeRepositoryDao groupeRepositoryDao;

    @Transactional
    public Enseignant saveEnseignant(Enseignant enseignant) {
        // Récupérer le département à partir du nom fourni dans le JSON
        String nomDepartement = enseignant.getDepartement().getNom();
        Departement departement = departementRepositoryDao.findByNomDep(nomDepartement);
        if (departement != null) {
            enseignant.setDepartement(departement);
        } else {
            throw new IllegalArgumentException("Departement avec le nom spécifié n'existe pas.");
        }

        // Récupérer la filière à partir du nom fourni dans le JSON
        String nomFiliere = enseignant.getFiliere().getNom();
        Filiere filiere = filiereRepositoryDao.findByNom(nomFiliere);
        if (filiere != null) {
            enseignant.setFiliere(filiere);
        } else {
            throw new IllegalArgumentException("Filiere avec le nom spécifié n'existe pas.");
        }

        // Récupérer le groupe à partir du nom fourni dans le JSON
        String nomGroupe = enseignant.getGroupe().getNomGroupe();
        Optional<Groupe> groupeOptional = groupeRepositoryDao.findByNomGroupe(nomGroupe);
        if (groupeOptional.isPresent()) {
            enseignant.setGroupe(groupeOptional.get());
        } else {
            throw new IllegalArgumentException("Groupe avec le nom spécifié n'existe pas.");
        }

        // Sauvegarder l'enseignant avec les associations appropriées
        return enseignantRepositoryDao.save(enseignant);
    }

    @Transactional
    public Enseignant getEnseignantById(Long id) {
        return enseignantRepositoryDao.findById(id).orElse(null);
    }



    @Transactional
    public List<Enseignant> getAllEnseignants() {
        return enseignantRepositoryDao.findAll();
    }
}
