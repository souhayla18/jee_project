package com.ensah.core.dao;

import com.ensah.core.bo.Departement;
import com.ensah.core.bo.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ISectorDao extends JpaRepository<Sector, Long> {
}
