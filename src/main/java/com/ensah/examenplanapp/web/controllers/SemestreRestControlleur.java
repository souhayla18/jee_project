package com.ensah.examenplanapp.web.controllers;

import com.ensah.examenplanapp.bo.Semestre;
import com.ensah.examenplanapp.services.exceptions.EntityNotFoundException;
import com.ensah.examenplanapp.services.impl.SemestreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/semestres")
public class SemestreRestControlleur {

    private final SemestreService semestreService;

    @Autowired
    public SemestreRestControlleur(SemestreService semestreService) {
        this.semestreService = semestreService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Semestre> getSemestreById(@PathVariable Long id) {
        Optional<Semestre> semestre = semestreService.getSemestreById(id);
        return semestre.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Semestre> getSemestreByName(@PathVariable String name) {
        Optional<Semestre> semestre = semestreService.getSemestreByName(name);
        return semestre.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Semestre>> getAllSemestres() {
        List<Semestre> semestres = semestreService.getAllSemestres();
        return new ResponseEntity<>(semestres, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Semestre> saveSemestre(@RequestBody Semestre semestre) {
        Semestre savedSemestre = semestreService.saveSemestre(semestre);
        return new ResponseEntity<>(savedSemestre, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Semestre> updateSemestre(@PathVariable Long id, @RequestBody Semestre semestre) {
        Semestre updatedSemestre = semestreService.updateSemestre(id, semestre);
        if (updatedSemestre != null) {
            return new ResponseEntity<>(updatedSemestre, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSemestre(@PathVariable Long id) {
        semestreService.deleteSemestreById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/nom/{name}")
    public ResponseEntity<Void> deleteSemestreByName(@PathVariable String name) {
        try {
            semestreService.deleteSemestreByName(name);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
