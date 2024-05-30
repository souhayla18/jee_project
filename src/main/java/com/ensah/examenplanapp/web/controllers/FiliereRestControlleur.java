package com.ensah.examenplanapp.web.controllers;

import com.ensah.examenplanapp.bo.Filiere;
import com.ensah.examenplanapp.services.exceptions.EntityNotFoundException;
import com.ensah.examenplanapp.services.impl.FiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/filieres")
public class FiliereRestControlleur {

    private final FiliereService filiereService;

    @Autowired
    public FiliereRestControlleur(FiliereService filiereService) {
        this.filiereService = filiereService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filiere> getFiliereById(@PathVariable Integer id) {
        Optional<Filiere> filiere = filiereService.getFiliereById(id);
        return filiere.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/nom/{nom}")
    public ResponseEntity<Filiere> getFiliereByNom(@PathVariable String nom) {
        Optional<Filiere> filiere = filiereService.getFiliereByNom(nom);
        return filiere.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Filiere>> getAllFilieres() {
        List<Filiere> filieres = filiereService.getAllFilieres();
        return new ResponseEntity<>(filieres, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Filiere> saveFiliere(@RequestBody Filiere filiere) {
        Filiere savedFiliere = filiereService.saveFiliere(filiere);
        return new ResponseEntity<>(savedFiliere, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Filiere> updateFiliere(@RequestBody Filiere filiereDetails) {
        try {
            Filiere updatedFiliere = filiereService.updateFiliere(filiereDetails);
            return new ResponseEntity<>(updatedFiliere, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFiliere(@PathVariable Integer id) {
        filiereService.deleteFiliereById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
