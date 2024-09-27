package com.ensah.core.services.Impl;

import com.ensah.core.bo.Educationalelement;
import com.ensah.core.bo.Group;
import com.ensah.core.bo.Person;
import com.ensah.core.bo.Professor;
import com.ensah.core.dao.IEducationalelementDao;
import com.ensah.core.dao.IPersonDao;
import com.ensah.core.services.IEducationalelementService;
import com.ensah.core.web.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class EducationalelementServiceImpl implements IEducationalelementService {

    @Autowired
    IEducationalelementDao elementDao;
    @Autowired
    IPersonDao personDao;

    public void addElement(Educationalelement element) {
        // Set professor using the provided ID
        if (element.getProfessor() != null && element.getProfessor().getIdPerson() != null) {
            Person professor = personDao.findById(element.getProfessor().getIdPerson())
                    .orElseThrow(() -> new ResourceNotFoundException("Professor", "id", element.getProfessor().getIdPerson()));
            if (professor instanceof Professor) {
                element.setProfessor((Professor) professor);
            } else {
                throw new IllegalArgumentException("Provided professor ID does not correspond to a Professor entity");
            }
        } else {
            throw new IllegalArgumentException("Professor ID must be provided");
        }

        if (element.getCoordinator() != null && element.getCoordinator().getIdPerson() != null) {
            Person coordinator = personDao.findById(element.getCoordinator().getIdPerson())
                    .orElseThrow(() -> new ResourceNotFoundException("Professor", "id", element.getCoordinator().getIdPerson()));
            if (coordinator instanceof Professor) {
                element.setCoordinator((Professor) coordinator);
            } else {
                throw new IllegalArgumentException("Provided coordinator ID does not correspond to a Professor entity");
            }
        } else {
            throw new IllegalArgumentException("Coordinator ID must be provided");
        }

        elementDao.save(element);
    }


    public void updateElement(Long elementId, Educationalelement pelement) {
        Optional<Educationalelement> educationalelementOptional = elementDao.findById(elementId);
        if (educationalelementOptional.isEmpty()) {
            throw new ResourceNotFoundException("Educationalelement", "id", elementId);
        }
        Educationalelement educationalelement = educationalelementOptional.get();

        if (pelement.getTitle() != null) {
            educationalelement.setTitle(pelement.getTitle());
        }

        if (pelement.getLevel() != null) {
            educationalelement.setLevel(pelement.getLevel());
        }

        if (pelement.getElementType() != null) {
            educationalelement.setElementType(pelement.getElementType());
        }

        if (pelement.getProfessor() != null) {
            educationalelement.setProfessor(pelement.getProfessor());
        }

        if (pelement.getCoordinator() != null) {
            educationalelement.setCoordinator(pelement.getCoordinator());
        }

        // Update any other fields as needed

        elementDao.save(educationalelement);
    }




    public List<Educationalelement> getAllElement() {
        return elementDao.findAll();    }

    public void deleteElement(Long id) {
        if (getElementById(id) != null){
            elementDao.deleteById(id);

        }

    }

    public Educationalelement getElementById(Long id) {
        return elementDao.findById(id).get();

    }

    public Educationalelement getElementByTitle(String title) {
        return elementDao.getPersonByTitle(title);
    }
}
