package com.ensah.core.dao;

import com.ensah.core.bo.Departement;
import com.ensah.core.bo.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IDepartementDao extends JpaRepository<Departement, Long> {
}
