package com.ensah.examenplanapp.dao;


import com.ensah.examenplanapp.bo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryDao extends JpaRepository<Role, Long> {
    Role findByNomRole(String nomRole);

}
