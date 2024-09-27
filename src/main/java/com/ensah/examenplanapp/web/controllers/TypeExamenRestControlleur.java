package com.ensah.examenplanapp.web.controllers;

import com.ensah.examenplanapp.bo.Type_Examen;
import com.ensah.examenplanapp.services.exceptions.EntityNotFoundException;
import com.ensah.examenplanapp.services.impl.Type_ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/type-examens")
public class TypeExamenRestControlleur {

    private final Type_ExamenService typeExamenService;

    @Autowired
    public TypeExamenRestControlleur(Type_ExamenService typeExamenService) {
        this.typeExamenService = typeExamenService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Type_Examen> getTypeExamenById(@PathVariable Long id) {
        Optional<Type_Examen> typeExamen = typeExamenService.getTypeExamenById(id);
        return typeExamen.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<Type_Examen> getTypeExamenByType(@PathVariable String type) {
        Optional<Type_Examen> typeExamen = typeExamenService.getTypeExamenByType(type);
        return typeExamen.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/type/{type}")
    public ResponseEntity<Void> deleteTypeExamenByType(@PathVariable String type) {
        try {
            typeExamenService.deleteTypeExamenByType(type);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Type_Examen>> getAllTypeExamens() {
        List<Type_Examen> typeExamens = typeExamenService.getAllTypeExamens();
        return new ResponseEntity<>(typeExamens, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Type_Examen> saveTypeExamen(@RequestBody Type_Examen typeExamen) {
        Type_Examen savedTypeExamen = typeExamenService.saveTypeExamen(typeExamen);
        return new ResponseEntity<>(savedTypeExamen, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Type_Examen> updateTypeExamen(@PathVariable Long id, @RequestBody Type_Examen typeExamen) {
        Type_Examen updatedTypeExamen = typeExamenService.updateTypeExamen(id, typeExamen);
        if (updatedTypeExamen != null) {
            return new ResponseEntity<>(updatedTypeExamen, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeExamen(@PathVariable Long id) {
        typeExamenService.deleteTypeExamenById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
