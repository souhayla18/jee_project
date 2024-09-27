package com.ensah.core.dao;


import com.ensah.core.bo.Educationalelement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEducationalelementDao extends JpaRepository<Educationalelement, Long> {

    Educationalelement getPersonByTitle(String title);

}
