package com.ensah.core.dao;

import com.ensah.core.bo.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUserDao extends CrudRepository<User,Long> {

    Optional<User> findByUsername(String username);

}