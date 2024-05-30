package com.ensah.examenplanapp.services;



import com.ensah.examenplanapp.bo.CadreAdministrateur;

import java.util.List;

public interface IAdministrateurService {
    List<CadreAdministrateur> getAllCadresAdministrateurs();
    CadreAdministrateur getCadreAdministrateurById(Long id);
    void deleteCadreAdministrateurById(Long id);
    void addCadreAdministrateur(CadreAdministrateur cadreAdministrateur);
    void updateCadreAdministrateur(CadreAdministrateur cadreAdministrateur);
    CadreAdministrateur getCadreAdministrateurByCin(String cin);
    void deleteCadreAdministrateurByCin(String cin);
}

