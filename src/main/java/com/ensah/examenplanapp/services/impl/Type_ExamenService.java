package com.ensah.examenplanapp.services.impl;

import com.ensah.examenplanapp.bo.Type_Examen;
import com.ensah.examenplanapp.dao.ITypeExamenRepositoryDao;
import com.ensah.examenplanapp.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Type_ExamenService {

    private final ITypeExamenRepositoryDao typeExamenRepositoryDao;

    @Autowired
    public Type_ExamenService(ITypeExamenRepositoryDao typeExamenRepositoryDao) {
        this.typeExamenRepositoryDao = typeExamenRepositoryDao;
    }

    public Optional<Type_Examen> getTypeExamenById(Long id) {
        return typeExamenRepositoryDao.findById(id);
    }

    public Optional<Type_Examen> getTypeExamenByType(String type) {
        return Optional.ofNullable(typeExamenRepositoryDao.findByType(type));
    }

    public List<Type_Examen> getAllTypeExamens() {
        return typeExamenRepositoryDao.findAll();
    }

    public Type_Examen saveTypeExamen(Type_Examen typeExamen) {
        return typeExamenRepositoryDao.save(typeExamen);
    }

    public void deleteTypeExamenById(Long id) {
        typeExamenRepositoryDao.deleteById(id);
    }
    public void deleteTypeExamenByType(String type) {
        Type_Examen typeExamen = typeExamenRepositoryDao.findByType(type);
        if (typeExamen != null) {
            typeExamenRepositoryDao.delete(typeExamen);
        } else {
            throw new EntityNotFoundException("Type_Examen not found with type " + type);
        }
    }

    public Type_Examen updateTypeExamen(Long id, Type_Examen typeExamen) {
        Optional<Type_Examen> existingTypeExamenOptional = typeExamenRepositoryDao.findById(id);
        if (existingTypeExamenOptional.isPresent()) {
            Type_Examen existingTypeExamen = existingTypeExamenOptional.get();
            // Mettre à jour les champs nécessaires
            existingTypeExamen.setType(typeExamen.getType());
            // Mettre à jour d'autres champs si nécessaire

            return typeExamenRepositoryDao.save(existingTypeExamen);
        } else {
            // Gérer le cas où le type d'examen avec l'ID spécifié n'existe pas
            return null; // Ou lever une exception appropriée
        }
    }
}
