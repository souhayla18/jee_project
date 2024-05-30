package com.ensah.examenplanapp.web.controllers;

import com.ensah.examenplanapp.bo.Element;
import com.ensah.examenplanapp.services.impl.Element_pedagogique_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elements")
public class ElementPedagogiqueRestControlleur {

    @Autowired
    private Element_pedagogique_Service elementService;

    // Create a new element
    @PostMapping
    public ResponseEntity<Element> createElement(@RequestBody Element element) {
        try {
            Element savedElement = elementService.saveElement(element);
            return new ResponseEntity<>(savedElement, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Get an element by ID
    @GetMapping("/{id}")
    public ResponseEntity<Element> getElementById(@PathVariable Long id) {
        Element element = elementService.getElementById(id);
        if (element != null) {
            return new ResponseEntity<>(element, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Delete an element by title
    @DeleteMapping("/deleteByTitle")
    public ResponseEntity<Void> deleteElementByTitle(@RequestParam String title) {
        try {
            elementService.deleteElementByTitle(title);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all elements (optional, for listing purposes)
    @GetMapping
    public ResponseEntity<List<Element>> getAllElements() {
        List<Element> elements = elementService.getAllElements();
        return new ResponseEntity<>(elements, HttpStatus.OK);
    }
}

