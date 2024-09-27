package com.ensah.examenplanapp.services.impl;

import com.ensah.examenplanapp.bo.Departement;
import com.ensah.examenplanapp.dao.IDepartementRepositoryDao;
import com.ensah.examenplanapp.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementService {

    @Autowired
    private IDepartementRepositoryDao departementRepositoryDao;

    public List<Departement> getAllDepartements() {
        return departementRepositoryDao.findAll();
    }

    public Departement getDepartementById(Long id) {
        return departementRepositoryDao.findById(id).orElse(null);
    }

    public Departement getDepartementByNom(String nomDep) {
        return departementRepositoryDao.findByNomDep(nomDep);
    }

    public Departement saveDepartement(Departement departement) {
        return departementRepositoryDao.save(departement);
    }

    public void deleteDepartement(Long id) {
        departementRepositoryDao.deleteById(id);
    }
    public void deleteDepartementByNom(String nomDep) {
        Departement departement = departementRepositoryDao.findByNomDep(nomDep);
        if (departement != null) {
            departementRepositoryDao.delete(departement);
        } else {
            throw new EntityNotFoundException("Departement not found with name " + nomDep);
        }
    }
}
