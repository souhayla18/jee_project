package com.ensah.examenplanapp.web.controllers;

import com.ensah.examenplanapp.bo.Examen;
import com.ensah.examenplanapp.services.impl.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/examens")
public class ExamenRestControlleur {

    @Autowired
    private ExamenService examenService;

    @PostMapping
    public ResponseEntity<Examen> saveExamen(@RequestBody Examen examen) {
        Examen savedExamen = examenService.saveExamen(examen);
        return new ResponseEntity<>(savedExamen, HttpStatus.CREATED);
    }

    @GetMapping("/epreuve/{epreuve}")
    public ResponseEntity<Examen> getExamenByEpreuve(@PathVariable String epreuve) {
        Examen examen = examenService.getExamenByEpreuve(epreuve);
        if (examen != null) {
            return new ResponseEntity<>(examen, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/epreuve/{epreuve}")
    public ResponseEntity<Void> deleteExamenByEpreuve(@PathVariable String epreuve) {
        examenService.deleteExamenByEpreuve(epreuve);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Examen>> getAllExamens() {
        List<Examen> examens = examenService.getAllExamens();
        return new ResponseEntity<>(examens, HttpStatus.OK);
    }
}
