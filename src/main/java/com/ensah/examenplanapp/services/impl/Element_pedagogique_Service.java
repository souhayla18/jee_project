package com.ensah.examenplanapp.services.impl;

import com.ensah.examenplanapp.bo.Element;
import com.ensah.examenplanapp.bo.Niveau;
import com.ensah.examenplanapp.bo.Type_Element;
import com.ensah.examenplanapp.dao.IElementRepositoryDao;
import com.ensah.examenplanapp.dao.INiveauRepositoryDao;
import com.ensah.examenplanapp.dao.ITypeElementRepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.lang.model.element.TypeElement;
import java.util.List;
import java.util.Optional;

@Service
public class Element_pedagogique_Service  {

    @Autowired
    private IElementRepositoryDao elementRepository;


    @Autowired
    private ITypeElementRepositoryDao typeElementRepository;

    @Autowired
    private INiveauRepositoryDao niveauRepository;

    @Transactional
    public Element saveElement(Element element) {
        // Récupérer le type d'élément à partir du nom fourni dans le JSON
        String typeElementName = element.getType_element().getTypeElement();
        Type_Element typeElement = typeElementRepository.findByTypeElement(typeElementName);
        if (typeElement != null) {
            element.setType_element(typeElement);
        } else {
            throw new IllegalArgumentException("TypeElement avec le nom spécifié n'existe pas.");
        }

        // Récupérer le niveau à partir du nom fourni dans le JSON
        String niveauName = element.getNiveau().getName();
        Niveau niveau = niveauRepository.findByName(niveauName);
        if (niveau != null) {
            element.setNiveau(niveau);
        } else {
            throw new IllegalArgumentException("Niveau avec le nom spécifié n'existe pas.");
        }

        // Sauvegarder l'élément avec les associations appropriées
        return elementRepository.save(element);
    }

    @Transactional
    public Element getElementById(Long id) {
        return elementRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteElementByTitle(String title) {
        Element element = elementRepository.findByTitle(title);
        if (element != null) {
            elementRepository.delete(element);
        } else {
            throw new IllegalArgumentException("Element avec le titre spécifié n'existe pas.");
        }
    }
    @Transactional
    public List<Element> getAllElements() {
        return elementRepository.findAll();
    }
}

