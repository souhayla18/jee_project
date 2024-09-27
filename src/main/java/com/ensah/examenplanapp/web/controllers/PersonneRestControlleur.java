package com.ensah.examenplanapp.web.controllers;

import com.ensah.examenplanapp.bo.Personne;
import com.ensah.examenplanapp.dao.IPersonneRepository;
import com.ensah.examenplanapp.services.impl.PersonneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/personnes")
public class PersonneRestControlleur {

    @Autowired
    private IPersonneRepository personneRepository;
    @Autowired
    private PersonneServiceImpl personneService;

    // Endpoint pour récupérer toutes les personnes
    @GetMapping
    public List<Personne> getAllPersonnes() {
        return personneRepository.findAll();
    }

    // Endpoint pour récupérer une personne par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Personne> getPersonneById(@PathVariable Long id) {
        Personne personne = personneRepository.findById(id).orElse(null);
        if (personne != null) {
            return new ResponseEntity<>(personne, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint pour récupérer une personne par son CIN
    @GetMapping("/by-cin/{cin}")
    public ResponseEntity<Personne> getPersonneByCin(@PathVariable String cin) {
        Personne personne = personneRepository.getPersonneByCin(cin);
        if (personne != null) {
            return new ResponseEntity<>(personne, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint pour récupérer une personne par son nom et son prénom
    @GetMapping("/by-nom-prenom")
    public ResponseEntity<Personne> getPersonneByNomAndPrenom(@RequestParam String nom, @RequestParam String prenom) {
        Personne personne = personneRepository.findByNomAndPrenom(nom, prenom);
        if (personne != null) {
            return new ResponseEntity<>(personne, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint pour créer une nouvelle personne
    @PostMapping
    public ResponseEntity<Personne> createPersonne(@RequestBody Personne personne) {
        Personne newPersonne = personneRepository.save(personne);
        return new ResponseEntity<>(newPersonne, HttpStatus.CREATED);
    }

    // Endpoint pour mettre à jour une personne existante
    @PutMapping("/{id}")
    public ResponseEntity<Personne> updatePersonne(@PathVariable Long id, @RequestBody Personne personneDetails) {
        Personne personne = personneRepository.findById(id).orElse(null);
        if (personne != null) {
            // Mettre à jour les champs nécessaires
            personne.setNom(personneDetails.getNom());
            personne.setPrenom(personneDetails.getPrenom());
            // Ajoutez d'autres champs à mettre à jour selon vos besoins
            Personne updatedPersonne = personneRepository.save(personne);
            return new ResponseEntity<>(updatedPersonne, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/cin/{cin}")
    public ResponseEntity<Personne> updatePersonneByCin(@PathVariable String cin, @RequestBody Personne personneDetails) {
        Personne personne = personneService.getPersonneByCin(cin);
        if (personne != null) {
            // Mettre à jour les champs nécessaires
            personne.setNom(personneDetails.getNom());
            personne.setPrenom(personneDetails.getPrenom());
            // Ajoutez d'autres champs à mettre à jour selon vos besoins
            Personne updatedPersonne = personneRepository.save(personne);
            return new ResponseEntity<>(updatedPersonne, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint pour supprimer une personne par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonnecascade(@PathVariable Long id) {
        personneRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/cin/{cin}")
    public ResponseEntity<Void> deleteByCinCascade(@PathVariable String cin) {
        Personne personne = personneService.getPersonneByCin(cin);
        if (personne != null) {
            personneRepository.delete(personne);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
