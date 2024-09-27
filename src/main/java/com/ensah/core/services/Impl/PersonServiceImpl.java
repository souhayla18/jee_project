package com.ensah.core.services.Impl;

import com.ensah.core.bo.Administrator;
import com.ensah.core.bo.Person;
import com.ensah.core.bo.Professor;
import com.ensah.core.dao.IDepartementDao;
import com.ensah.core.dao.IPersonDao;
import com.ensah.core.dao.ISectorDao;
import com.ensah.core.dto.PersonDTO;
import com.ensah.core.web.exceptions.ResourceNotFoundException;
import com.ensah.core.services.IPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PersonServiceImpl implements IPersonService {

    @Autowired
    IPersonDao personDao;
    @Autowired
    IDepartementDao departementDao;
    @Autowired
    ISectorDao sectorDao;



    public void addPerson(PersonDTO personDTO) {
        System.out.println("Received type: " + personDTO.getType());
        Person person;
        if ("Professor".equals(personDTO.getType())){
            Professor professor = new Professor();
            professor.setType(personDTO.getType()); // Set the type attribute
            professor.setFirstName(personDTO.getFirstName());
            professor.setLastName(personDTO.getLastName());
            professor.setCin(personDTO.getCin());
            professor.setSpeciality(personDTO.getSpeciality());
            professor.setDepartement(departementDao.findById(personDTO.getIdDepartement()).orElseThrow(() -> new IllegalArgumentException("Invalid departement ID")));
            professor.setSector(sectorDao.findById(personDTO.getIdSector()).orElseThrow(() -> new IllegalArgumentException("Invalid Sector ID")));

            // Set other professor-specific fields here
            person = professor;
        } else if ("Administrator".equals(personDTO.getType())) {
            Administrator administrator = new Administrator();
            administrator.setType(personDTO.getType()); // Set the type attribute
            administrator.setFirstName(personDTO.getFirstName());
            administrator.setLastName(personDTO.getLastName());
            administrator.setCin(personDTO.getCin());
            administrator.setGrade(personDTO.getGrade());
            // Set other administrator-specific fields here
            person = administrator;
        } else {
            throw new IllegalArgumentException("Invalid person data");
        }

        personDao.save(person);
    }


    @Override
    public void updatePerson(Long personId, PersonDTO personDTO) {
        Optional<Person> personOptional = personDao.findById(personId);
        if (personOptional.isEmpty()) {
            throw new ResourceNotFoundException("Person", "id", personId);
        }

        Person person = personOptional.get();

        // Update common fields if they are not null
        if (personDTO.getFirstName() != null) {
            person.setFirstName(personDTO.getFirstName());
        }
        if (personDTO.getLastName() != null) {
            person.setLastName(personDTO.getLastName());
        }
        if (personDTO.getCin() != null) {
            person.setCin(personDTO.getCin());
        }

        // Update specific fields based on the type of person
        if (person instanceof Professor) {
            if (personDTO.getSpeciality() != null) {
                ((Professor) person).setSpeciality(personDTO.getSpeciality());
            }
            // Update other professor-specific fields if necessary
        } else if (person instanceof Administrator) {
            if (personDTO.getGrade() != null) {
                ((Administrator) person).setGrade(personDTO.getGrade());
            }
            // Update other administrator-specific fields if necessary
        } else {
            throw new IllegalArgumentException("Invalid person type");
        }

        personDao.save(person);
    }


    public List<Person> getAllPersons() {
        return personDao.findAll();

    }

    public void deletePerson(Long id) {
        if (getPersonById(id) != null){
            personDao.deleteById(id);
        }

    }

    public Person getPersonById(Long id) {
        Optional<Person> personOptional = personDao.findById(id);
        if (personOptional.isPresent()) {
            return personOptional.get();
        } else {
            System.out.println("Person with id " + id + " doesn't exist");
            return null; // or throw an exception
        }
    }


    public Person getPersonByCin(String cin) {
        return personDao.getPersonByCin(cin);
    }
}
