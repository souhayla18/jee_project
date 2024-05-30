package com.ensah.examenplanapp.web.controllers;

import com.ensah.examenplanapp.bo.Salle;
import com.ensah.examenplanapp.services.exceptions.EntityNotFoundException;
import com.ensah.examenplanapp.services.impl.salleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/salles")
public class SalleRestControlleur {

    private final salleService salleService;

    @Autowired
    public SalleRestControlleur(salleService salleService) {
        this.salleService = salleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Salle> getSalleById(@PathVariable Long id) {
        Optional<Salle> salle = salleService.getSalleById(id);
        return salle.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/nom/{nom}")
    public ResponseEntity<Salle> getSalleByNom(@PathVariable String nom) {
        Optional<Salle> salle = salleService.getSalleByNom(nom);
        return salle.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Salle>> getAllSalles() {
        List<Salle> salles = salleService.getAllSalles();
        return new ResponseEntity<>(salles, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Salle> saveSalle(@RequestBody Salle salle) {
        Salle savedSalle = salleService.saveSalle(salle);
        return new ResponseEntity<>(savedSalle, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Salle> updateSalle(@PathVariable Long id, @RequestBody Salle salle) {
        Salle updatedSalle = salleService.updateSalle(id, salle);
        if (updatedSalle != null) {
            return new ResponseEntity<>(updatedSalle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalle(@PathVariable Long id) {
        salleService.deleteSalleById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/nom/{nomSalle}")
    public ResponseEntity<Void> deleteSalleByNom(@PathVariable String nomSalle) {
        try {
            salleService.deleteSalleByNom(nomSalle);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
