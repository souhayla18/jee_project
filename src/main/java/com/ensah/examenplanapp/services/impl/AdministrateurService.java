package com.ensah.examenplanapp.services.impl;

import com.ensah.examenplanapp.bo.CadreAdministrateur;
import com.ensah.examenplanapp.dao.IAdministrateurRepositoryDao;
import com.ensah.examenplanapp.services.IAdministrateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdministrateurService implements IAdministrateurService {

    @Autowired
    private IAdministrateurRepositoryDao administrateurRepositoryDao;

    @Override
    public List<CadreAdministrateur> getAllCadresAdministrateurs() {
        return administrateurRepositoryDao.findAll();
    }

    @Override
    public CadreAdministrateur getCadreAdministrateurById(Long id) {
        return administrateurRepositoryDao.findById(id).orElse(null);
    }

    @Override
    public void deleteCadreAdministrateurById(Long id) {
        administrateurRepositoryDao.deleteById(id);
    }

    @Override
    public void addCadreAdministrateur(CadreAdministrateur cadreAdministrateur) {
        administrateurRepositoryDao.save(cadreAdministrateur);
    }
    @Override
    public void deleteCadreAdministrateurByCin(String cin) {
        administrateurRepositoryDao.deleteByCin(cin);
    }
    @Override
    public CadreAdministrateur getCadreAdministrateurByCin(String cin) {
        return administrateurRepositoryDao.findByCin(cin).orElse(null);
    }

    @Override
    public void updateCadreAdministrateur(CadreAdministrateur cadreAdministrateur) {
        administrateurRepositoryDao.save(cadreAdministrateur);
    }
}
