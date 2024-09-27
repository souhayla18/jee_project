package com.ensah.examenplanapp.services.impl;
import com.ensah.examenplanapp.bo.*;
import com.ensah.examenplanapp.dao.*;
import com.ensah.examenplanapp.services.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SurveillanceService {

    private final ISurveillanceRepositoryDao surveillanceRepositoryDao;
    private final ISalleRepositoryDao salleRepositoryDao;
    private final IExamenRepositoryDao examenRepositoryDao;
    private final IAdministrateurRepositoryDao cadreAdminRepositoryDao;

    @Autowired
    public SurveillanceService(ISurveillanceRepositoryDao surveillanceRepositoryDao, ISalleRepositoryDao salleRepositoryDao,
                               IExamenRepositoryDao examenRepositoryDao, IAdministrateurRepositoryDao cadreAdminRepositoryDao) {
        this.surveillanceRepositoryDao = surveillanceRepositoryDao;
        this.salleRepositoryDao = salleRepositoryDao;
        this.examenRepositoryDao = examenRepositoryDao;
        this.cadreAdminRepositoryDao = cadreAdminRepositoryDao;
    }

    public surveillance saveSurveillance(surveillance surveillance) {
        // Récupérer le nom de la salle de la surveillance
        String nomSalle = surveillance.getSalle().getNom();

        // Vérifier si la salle avec le nom spécifié existe
        Optional<Salle> salleOptional = salleRepositoryDao.findByNom(nomSalle);
        if (salleOptional.isEmpty()) {
            throw new IllegalArgumentException("Salle avec le nom spécifié n'existe pas.");
        }


        // Récupérer l'épreuve de la surveillance
        String epreuve = surveillance.getExamen().getEpreuve();

        // Vérifier si l'épreuve avec le nom spécifié existe
        Optional<Examen> examenOptional = examenRepositoryDao.findByEpreuve(epreuve);
        if (examenOptional.isEmpty()) {
            throw new IllegalArgumentException("Examen avec l'épreuve spécifiée n'existe pas.");
        }

        // Récupérer le grade admin de la surveillance
        String gradeAdmin = surveillance.getAdmin().getGradeAdmin();

        // Vérifier si le cadre admin avec le grade spécifié existe
        Optional<CadreAdministrateur> cadreAdminOptional = cadreAdminRepositoryDao.findByGradeAdmin(gradeAdmin);
        if (cadreAdminOptional.isEmpty()) {
            throw new IllegalArgumentException("Cadre admin avec le grade spécifié n'existe pas.");
        }

        // Associer la salle, l'examen et le cadre admin à la surveillance
        surveillance.setSalle(salleOptional.get());
        surveillance.setExamen(examenOptional.get());
        surveillance.setAdmin(cadreAdminOptional.get());

        // Sauvegarder la surveillance dans la base de données
        return surveillanceRepositoryDao.save(surveillance);
    }

    public surveillance updateSurveillance(Long id, surveillance updatedSurveillance) {
        // Récupérer l'entité surveillance existante par son ID
        Optional<surveillance> existingSurveillanceOptional = surveillanceRepositoryDao.findById(id);
        if (existingSurveillanceOptional.isPresent()) {
            surveillance existingSurveillance = existingSurveillanceOptional.get();

            // Récupérer le nom de la salle de la surveillance mise à jour
            String nomSalle = updatedSurveillance.getSalle().getNom();
            // Vérifier si une salle avec le nom spécifié existe
            Optional<Salle> salleOptional = salleRepositoryDao.findByNom(nomSalle);
            if (salleOptional.isPresent()) {
                existingSurveillance.setSalle(salleOptional.get());
            } else {
                throw new IllegalArgumentException("Salle avec le nom spécifié n'existe pas.");
            }

            // Récupérer l'épreuve de la surveillance mise à jour
            String epreuve = updatedSurveillance.getExamen().getEpreuve();
            // Vérifier si un examen avec l'épreuve spécifiée existe
            Optional<Examen> examenOptional = examenRepositoryDao.findByEpreuve(epreuve);
            if (examenOptional.isPresent()) {
                existingSurveillance.setExamen(examenOptional.get());
            } else {
                throw new IllegalArgumentException("Examen avec l'épreuve spécifiée n'existe pas.");
            }

            // Récupérer le grade admin de la surveillance mise à jour
            String gradeAdmin = updatedSurveillance.getAdmin().getGradeAdmin();
            // Vérifier si un cadre admin avec le grade spécifié existe
            Optional<CadreAdministrateur> cadreAdminOptional = cadreAdminRepositoryDao.findByGradeAdmin(gradeAdmin);
            if (cadreAdminOptional.isPresent()) {
                existingSurveillance.setAdmin(cadreAdminOptional.get());
            } else {
                throw new IllegalArgumentException("Cadre admin avec le grade spécifié n'existe pas.");
            }

            // Sauvegarder la surveillance mise à jour dans la base de données
            return surveillanceRepositoryDao.save(existingSurveillance);
        } else {
            throw new EntityNotFoundException("Surveillance not found with ID: " + id);
        }
    }


    public List<surveillance> getAllSurveillances() {
        return surveillanceRepositoryDao.findAll();
    }
    public void deleteSurveillance(Long id) {
        surveillanceRepositoryDao.deleteById(id);
    }

    public Optional<surveillance> getSurveillanceById(Long id) {
        return surveillanceRepositoryDao.findById(id);
    }

    // Autres méthodes du service...
}

