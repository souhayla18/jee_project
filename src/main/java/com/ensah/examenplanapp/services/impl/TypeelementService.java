package com.ensah.examenplanapp.services.impl;

import com.ensah.examenplanapp.bo.Type_Element;
import com.ensah.examenplanapp.dao.ITypeElementRepositoryDao;
import com.ensah.examenplanapp.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional // Ajout de l'annotation @Transactional pour gérer les transactions au niveau du service
public class TypeelementService {

    private final ITypeElementRepositoryDao typeElementRepositoryDao;

    @Autowired
    public TypeelementService(ITypeElementRepositoryDao typeElementRepositoryDao) {
        this.typeElementRepositoryDao = typeElementRepositoryDao;
    }

    @Transactional(readOnly = true)
    public Optional<Type_Element> getTypeElementById(Long id) {
        return typeElementRepositoryDao.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Type_Element> getTypeElementByTypeElement(String typeElement) {
        return Optional.ofNullable(typeElementRepositoryDao.findByTypeElement(typeElement));
    }

    @Transactional(readOnly = true)
    public List<Type_Element> getAllTypeElements() {
        return typeElementRepositoryDao.findAll();
    }

    public Type_Element saveTypeElement(Type_Element typeElement) {
        return typeElementRepositoryDao.save(typeElement);
    }

    public void deleteTypeElementById(Long id) {
        typeElementRepositoryDao.deleteById(id);
    }
    public void deleteTypeElementByTypeElement(String typeElement) {
        Type_Element typeElementObject = typeElementRepositoryDao.findByTypeElement(typeElement);
        if (typeElementObject != null) {
            typeElementRepositoryDao.delete(typeElementObject);
        } else {
            throw new EntityNotFoundException("Type_Element not found with type element " + typeElement);
        }
    }

    public Type_Element updateTypeElement(Long id, Type_Element typeElement) {
        Optional<Type_Element> existingTypeElementOptional = typeElementRepositoryDao.findById(id);
        if (existingTypeElementOptional.isPresent()) {
            Type_Element existingTypeElement = existingTypeElementOptional.get();
            // Mettre à jour les champs nécessaires
            existingTypeElement.setTypeElement(typeElement.getTypeElement());
            // Mettre à jour d'autres champs si nécessaire

            return typeElementRepositoryDao.save(existingTypeElement);
        } else {
            // Gérer le cas où le type d'élément avec l'ID spécifié n'existe pas
            return null; // Ou lever une exception appropriée
        }
    }
}
