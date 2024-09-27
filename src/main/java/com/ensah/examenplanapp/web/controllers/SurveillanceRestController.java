package com.ensah.examenplanapp.web.controllers;

import com.ensah.examenplanapp.bo.surveillance;
import com.ensah.examenplanapp.services.impl.SurveillanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/surveillances")
public class SurveillanceRestController {

    @Autowired
    private SurveillanceService surveillanceService;

    @PostMapping
    public ResponseEntity<surveillance> saveSurveillance(@RequestBody surveillance surveillance) {
        surveillance savedSurveillance = surveillanceService.saveSurveillance(surveillance);
        return new ResponseEntity<>(savedSurveillance, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<surveillance> updateSurveillance(@PathVariable Long id, @RequestBody surveillance updatedSurveillance) {
        surveillance surveillance = surveillanceService.updateSurveillance(id, updatedSurveillance);
        return new ResponseEntity<>(surveillance, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<surveillance>> getAllSurveillances() {
        List<surveillance> surveillances = surveillanceService.getAllSurveillances();
        return new ResponseEntity<>(surveillances, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<surveillance> getSurveillanceById(@PathVariable Long id) {
        Optional<surveillance> surveillance = surveillanceService.getSurveillanceById(id);
        return surveillance.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurveillance(@PathVariable Long id) {
        surveillanceService.deleteSurveillance(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
