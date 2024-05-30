package com.ensah.examenplanapp.web.controllers;

import com.ensah.examenplanapp.bo.CadreAdministrateur;
import com.ensah.examenplanapp.services.IAdministrateurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cadres-administrateurs")
public class CadreAdminRestController {

    @Autowired
    private IAdministrateurService administrateurService;

    @GetMapping
    public List<CadreAdministrateur> getAllCadreAdministrateurs() {
        return administrateurService.getAllCadresAdministrateurs();
    }
    @RequestMapping(value = "/cin/{cin}", method = RequestMethod.DELETE)
    public void deleteCadreAdministrateurByCin(@PathVariable String cin) {
        administrateurService.deleteCadreAdministrateurByCin(cin);
    }

    @GetMapping("/{id}")
    public CadreAdministrateur getCadreAdministrateurById(@PathVariable Long id) {
        return administrateurService.getCadreAdministrateurById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public CadreAdministrateur addCadreAdministrateur(@RequestBody CadreAdministrateur cadreAdministrateur) {
        administrateurService.addCadreAdministrateur(cadreAdministrateur);
        return cadreAdministrateur;
    }

    @RequestMapping(value = "/{cin}", method = RequestMethod.PUT)
    public CadreAdministrateur updateCadreAdministrateur(@PathVariable String cin, @RequestBody CadreAdministrateur cadreAdministrateurDetails) {
        CadreAdministrateur existingCadre = administrateurService.getCadreAdministrateurByCin(cin);
        if (existingCadre != null) {
            cadreAdministrateurDetails.setIdPersonne(existingCadre.getIdPersonne()); // Ensure the ID is set correctly
            administrateurService.updateCadreAdministrateur(cadreAdministrateurDetails);
            return cadreAdministrateurDetails;
        } else {
            // Handle case where the admin doesn't exist
            return null;
        }
    }
}



