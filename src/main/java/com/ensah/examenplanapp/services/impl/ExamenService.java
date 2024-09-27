package com.ensah.examenplanapp.services.impl;

import com.ensah.examenplanapp.bo.Examen;
import com.ensah.examenplanapp.bo.Semestre;
import com.ensah.examenplanapp.bo.Session;
import com.ensah.examenplanapp.bo.Type_Examen;
import com.ensah.examenplanapp.bo.Element;
import com.ensah.examenplanapp.dao.IExamenRepositoryDao;
import com.ensah.examenplanapp.dao.ISemestreRepositoryDao;
import com.ensah.examenplanapp.dao.ISessionRepositoryDao;
import com.ensah.examenplanapp.dao.ITypeExamenRepositoryDao;
import com.ensah.examenplanapp.dao.IElementRepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ExamenService {

    @Autowired
    private IExamenRepositoryDao examenRepositoryDao;

    @Autowired
    private ITypeExamenRepositoryDao typeExamenRepositoryDao;

    @Autowired
    private ISemestreRepositoryDao semestreRepositoryDao;

    @Autowired
    private ISessionRepositoryDao sessionRepositoryDao;

    @Autowired
    private IElementRepositoryDao elementRepositoryDao;

    @Transactional
    public Examen saveExamen(Examen examen) {
        // Récupérer le type d'examen à partir du nom fourni dans le JSON
        String typeExamenName = examen.getTypeExamen().getType();
        Type_Examen typeExamen = typeExamenRepositoryDao.findByType(typeExamenName);
        if (typeExamen != null) {
            examen.setTypeExamen(typeExamen);
        } else {
            throw new IllegalArgumentException("Type_Examen avec le nom spécifié n'existe pas.");
        }

        // Récupérer le semestre à partir du nom fourni dans le JSON
        String semestreName = examen.getSemestre().getName();
        Semestre semestre = semestreRepositoryDao.findByName(semestreName);
        if (semestre != null) {
            examen.setSemestre(semestre);
        } else {
            throw new IllegalArgumentException("Semestre avec le nom spécifié n'existe pas.");
        }

        // Récupérer la session à partir du nom fourni dans le JSON
        String sessionName = examen.getSession().getName();
        Session session = sessionRepositoryDao.findByName(sessionName);
        if (session != null) {
            examen.setSession(session);
        } else {
            throw new IllegalArgumentException("Session avec le nom spécifié n'existe pas.");
        }

        // Récupérer l'élément pédagogique à partir du titre fourni dans le JSON
        String elementTitle = examen.getElement().getTitle();
        Element element = elementRepositoryDao.findByTitle(elementTitle);
        if (element != null) {
            examen.setElement(element);
        } else {
            throw new IllegalArgumentException("Element avec le titre spécifié n'existe pas.");
        }

        // Sauvegarder l'examen avec les associations appropriées
        return examenRepositoryDao.save(examen);
    }

    @Transactional
    public Examen getExamenByEpreuve(String epreuve) {
        return examenRepositoryDao.findByEpreuve(epreuve).orElse(null);
    }

    @Transactional
    public void deleteExamenByEpreuve(String epreuve) {
        Examen examen = examenRepositoryDao.findByEpreuve(epreuve).orElse(null);
        if (examen != null) {
            examenRepositoryDao.delete(examen);
        } else {
            throw new IllegalArgumentException("Examen avec l'épreuve spécifiée n'existe pas.");
        }
    }

    @Transactional
    public List<Examen> getAllExamens() {
        return examenRepositoryDao.findAll();
    }
}
