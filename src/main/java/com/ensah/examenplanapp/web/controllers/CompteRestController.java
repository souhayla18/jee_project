package com.ensah.examenplanapp.web.controllers;

import com.ensah.examenplanapp.bo.Compte;
import com.ensah.examenplanapp.dao.ICompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comptes")
public class CompteRestController {

    private final ICompteRepository compteRepository;

    @Autowired
    public CompteRestController(ICompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    @GetMapping
    public ResponseEntity<List<Compte>> getAllComptes() {
        List<Compte> comptes = compteRepository.findAll();
        return new ResponseEntity<>(comptes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compte> getCompteById(@PathVariable Long id) {
        Compte compte = compteRepository.findById(id).orElse(null);
        if (compte != null) {
            return new ResponseEntity<>(compte, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Compte> createCompte(@RequestBody Compte compte) {
        Compte savedCompte = compteRepository.save(compte);
        return new ResponseEntity<>(savedCompte, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Compte> updateCompte(@PathVariable Long id, @RequestBody Compte compte) {
        if (compteRepository.existsById(id)) {
            compte.setIdCompte(id);
            Compte updatedCompte = compteRepository.save(compte);
            return new ResponseEntity<>(updatedCompte, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompte(@PathVariable Long id) {
        if (compteRepository.existsById(id)) {
            compteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/login/{username}")
    public ResponseEntity<Compte> getCompteByLogin(@PathVariable String username) {
        Compte compte = compteRepository.getCompteByLogin(username);
        if (compte != null) {
            return new ResponseEntity<>(compte, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
