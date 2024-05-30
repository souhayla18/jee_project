package com.ensah.examenplanapp.web.controllers;

import com.ensah.examenplanapp.bo.Groupe;
import com.ensah.examenplanapp.services.impl.GroupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/groupes")
public class GroupeRestController {

    private final GroupeService groupeService;

    @Autowired
    public GroupeRestController(GroupeService groupeService) {
        this.groupeService = groupeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Groupe> getGroupeById(@PathVariable Long id) {
        Optional<Groupe> groupe = groupeService.getGroupeById(id);
        return groupe.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/nom/{nom}")
    public ResponseEntity<Groupe> getGroupeByNom(@PathVariable String nom) {
        Optional<Groupe> groupe = groupeService.getGroupeByNom(nom);
        return groupe.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Groupe>> getAllGroupes() {
        List<Groupe> groupes = groupeService.getAllGroupes();
        return new ResponseEntity<>(groupes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Groupe> saveGroupe(@RequestBody Groupe groupe) {
        Groupe savedGroupe = groupeService.saveGroupe(groupe);
        return new ResponseEntity<>(savedGroupe, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Groupe> updateGroupe(@PathVariable Long id, @RequestBody Groupe groupe) {
        Groupe updatedGroupe = groupeService.updateGroupe(id, groupe);
        if (updatedGroupe != null) {
            return new ResponseEntity<>(updatedGroupe, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroupe(@PathVariable Long id) {
        groupeService.deleteGroupeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
