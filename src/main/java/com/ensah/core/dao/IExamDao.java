package com.ensah.core.dao;

import com.ensah.core.bo.Exam;
import com.ensah.core.bo.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IExamDao extends JpaRepository<Exam, Long> {






}
