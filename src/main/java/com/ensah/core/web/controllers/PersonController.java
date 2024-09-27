package com.ensah.core.web.controllers;


import com.ensah.core.bo.Person;
import com.ensah.core.dto.PersonDTO;
import com.ensah.core.services.IPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/personnel")
@RequiredArgsConstructor
public class PersonController {

    @Autowired
    IPersonService personService;

    @PostMapping()
    public ResponseEntity<Void> addPerson(@RequestBody PersonDTO personDTO) {
        personService.addPerson(personDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{personnelId}")
    public ResponseEntity<Void> updatePerson(@PathVariable Long personnelId, @RequestBody PersonDTO personDTO) {
        personService.updatePerson(personnelId, personDTO);
        return ResponseEntity.ok().build();

    }
    @GetMapping
    public ResponseEntity<List<Person>> getAllPersonRS() {
        return ResponseEntity.ok(personService.getAllPersons());
    }


    @GetMapping("/{personnelId}")
    public ResponseEntity<Person> getOnePersonRS(@PathVariable Long personnelId) {
        return ResponseEntity.ok(personService.getPersonById(personnelId));

    }
    @DeleteMapping("/{personnelId}")
    public ResponseEntity<Void> deletePersonRS(@PathVariable Long personnelId) {
        personService.deletePerson(personnelId);
        return ResponseEntity.noContent().build();
    }

}


