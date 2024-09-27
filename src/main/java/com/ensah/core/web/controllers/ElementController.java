package com.ensah.core.web.controllers;


import com.ensah.core.bo.Administrator;
import com.ensah.core.bo.Educationalelement;
import com.ensah.core.bo.Professor;
import com.ensah.core.services.IEducationalelementService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("api/Educationalelement")
public class ElementController {

    @Autowired
    IEducationalelementService educationalelementService;


    @PostMapping()
    public ResponseEntity<Educationalelement> addEducationalelementRS(@RequestBody Educationalelement educationalelement) {
       educationalelementService.addElement(educationalelement);
        return ResponseEntity.noContent().build();
    }



    @PutMapping("/{EducationalelementnelId}")
    public ResponseEntity<Educationalelement> updateEducationalelementRS(@PathVariable Long EducationalelementnelId,@Valid @RequestBody Educationalelement EducationalelementnelDetails) {
        educationalelementService.updateElement(EducationalelementnelId, EducationalelementnelDetails);
        return ResponseEntity.noContent().build();
    }



    @GetMapping
    public ResponseEntity<List<Educationalelement>> getAllEducationalelementRS() {
        return ResponseEntity.ok(educationalelementService.getAllElement());
    }



    @GetMapping("/{EducationalelementnelId}")
    public ResponseEntity<Educationalelement> getOneEducationalelementRS(@PathVariable Long EducationalelementnelId) {
        return ResponseEntity.ok(educationalelementService.getElementById(EducationalelementnelId));

    }

    @DeleteMapping("/{EducationalelementnelId}")
    public ResponseEntity<Void> deleteEducationalelementRS(@PathVariable Long EducationalelementnelId) {
        educationalelementService.deleteElement(EducationalelementnelId);
        return ResponseEntity.noContent().build();
    }

}


