package com.ensah.examenplanapp.web.controllers;

import com.ensah.examenplanapp.bo.Type_Element;
import com.ensah.examenplanapp.dao.ITypeElementRepositoryDao;
import com.ensah.examenplanapp.services.exceptions.EntityNotFoundException;
import com.ensah.examenplanapp.services.impl.TypeelementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type-elements")
public class TypeElementRestControlleur {

    @Autowired
    private ITypeElementRepositoryDao typeElementRepositoryDao;
    @Autowired
    private TypeelementService typeElementService;

    // Endpoint pour récupérer tous les types d'éléments
    @GetMapping
    public List<Type_Element> getAllTypeElements() {
        return typeElementRepositoryDao.findAll();
    }

    // Endpoint pour récupérer un type d'élément par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Type_Element> getTypeElementById(@PathVariable Long id) {
        Type_Element typeElement = typeElementRepositoryDao.findById(id).orElse(null);
        if (typeElement != null) {
            return new ResponseEntity<>(typeElement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint pour récupérer un type d'élément par son nom
    @GetMapping("/by-type/{type}")
    public ResponseEntity<Type_Element> getTypeElementByType(@PathVariable String type) {
        Type_Element typeElement = typeElementRepositoryDao.findByTypeElement(type);
        if (typeElement != null) {
            return new ResponseEntity<>(typeElement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint pour créer un nouveau type d'élément
    @PostMapping
    public ResponseEntity<Type_Element> createTypeElement(@RequestBody Type_Element typeElement) {
        Type_Element newTypeElement = typeElementRepositoryDao.save(typeElement);
        return new ResponseEntity<>(newTypeElement, HttpStatus.CREATED);
    }

    // Endpoint pour mettre à jour un type d'élément existant
    @PutMapping("/{id}")
    public ResponseEntity<Type_Element> updateTypeElement(@PathVariable Long id, @RequestBody Type_Element typeElementDetails) {
        Type_Element typeElement = typeElementRepositoryDao.findById(id).orElse(null);
        if (typeElement != null) {
            // Mettre à jour les champs nécessaires
            typeElement.setTypeElement(typeElementDetails.getTypeElement());
            // Ajoutez d'autres champs à mettre à jour selon vos besoins
            Type_Element updatedTypeElement = typeElementRepositoryDao.save(typeElement);
            return new ResponseEntity<>(updatedTypeElement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint pour supprimer un type d'élément par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeElement(@PathVariable Long id) {
        typeElementRepositoryDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/element/{typeElement}")
    public ResponseEntity<Void> deleteTypeElementByTypeElement(@PathVariable String typeElement) {
        try {
            typeElementService.deleteTypeElementByTypeElement(typeElement);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
