package com.ensah.examenplanapp.services.impl;

import com.ensah.examenplanapp.bo.Semestre;
import com.ensah.examenplanapp.dao.ISemestreRepositoryDao;
import com.ensah.examenplanapp.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SemestreService {

    private final ISemestreRepositoryDao semestreRepositoryDao;

    @Autowired
    public SemestreService(ISemestreRepositoryDao semestreRepositoryDao) {
        this.semestreRepositoryDao = semestreRepositoryDao;
    }

    public Optional<Semestre> getSemestreById(Long id) {
        return semestreRepositoryDao.findById(id);
    }

    public Optional<Semestre> getSemestreByName(String name) {
        return Optional.ofNullable(semestreRepositoryDao.findByName(name));
    }

    public List<Semestre> getAllSemestres() {
        return semestreRepositoryDao.findAll();
    }

    public Semestre saveSemestre(Semestre semestre) {
        return semestreRepositoryDao.save(semestre);
    }

    public void deleteSemestreById(Long id) {
        semestreRepositoryDao.deleteById(id);
    }
    public void deleteSemestreByName(String name) {
        Semestre semestre = semestreRepositoryDao.findByName(name);
        if (semestre != null) {
            semestreRepositoryDao.delete(semestre);
        } else {
            throw new EntityNotFoundException("Semestre not found with name " + name);
        }
    }

    public Semestre updateSemestre(Long id, Semestre semestre) {
        Optional<Semestre> existingSemestreOptional = semestreRepositoryDao.findById(id);
        if (existingSemestreOptional.isPresent()) {
            Semestre existingSemestre = existingSemestreOptional.get();
            // Mettre à jour les champs nécessaires
            existingSemestre.setName(semestre.getName());
            // Mettre à jour d'autres champs si nécessaire

            return semestreRepositoryDao.save(existingSemestre);
        } else {
            // Gérer le cas où le semestre avec l'ID spécifié n'existe pas
            return null; // Ou lever une exception appropriée
        }
    }
}
