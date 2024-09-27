package com.ensah.examenplanapp.web.controllers;

import com.ensah.examenplanapp.bo.Niveau;
import com.ensah.examenplanapp.services.exceptions.EntityNotFoundException;
import com.ensah.examenplanapp.services.impl.NiveauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/niveaux")
public class NiveauRestControlleur {

    private final NiveauService niveauService;

    @Autowired
    public NiveauRestControlleur(NiveauService niveauService) {
        this.niveauService = niveauService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Niveau> getNiveauById(@PathVariable Long id) {
        Optional<Niveau> niveau = niveauService.getNiveauById(id);
        return niveau.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/nom/{name}")
    public ResponseEntity<Void> deleteNiveauByNom(@PathVariable String name) {
        try {
            niveauService.deleteNiveauByNom(name);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Niveau> getNiveauByName(@PathVariable String name) {
        Optional<Niveau> niveau = niveauService.getNiveauByName(name);
        return niveau.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Niveau>> getAllNiveaux() {
        List<Niveau> niveaux = niveauService.getAllNiveaux();
        return new ResponseEntity<>(niveaux, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Niveau> saveNiveau(@RequestBody Niveau niveau) {
        Niveau savedNiveau = niveauService.saveNiveau(niveau);
        return new ResponseEntity<>(savedNiveau, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Niveau> updateNiveau(@PathVariable Long id, @RequestBody Niveau niveau) {
        Niveau updatedNiveau = niveauService.updateNiveau(id, niveau);
        if (updatedNiveau != null) {
            return new ResponseEntity<>(updatedNiveau, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNiveau(@PathVariable Long id) {
        niveauService.deleteNiveauById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
