package com.ensah.core.dao;

import com.ensah.core.bo.Exam;
import com.ensah.core.bo.Group;
import com.ensah.core.bo.Monitoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IMonitoringDao extends JpaRepository<Monitoring, Long> {


    @Query("SELECT m FROM Monitoring m WHERE m.dateExam = :dateExam")
    List<Monitoring> findByDateExam(@Param("dateExam") String dateExam);
}
