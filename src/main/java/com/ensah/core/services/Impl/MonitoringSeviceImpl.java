package com.ensah.core.services.Impl;

import com.ensah.core.bo.*;
import com.ensah.core.dao.IExamDao;
import com.ensah.core.dao.IMonitoringDao;
import com.ensah.core.dao.IPersonDao;
import com.ensah.core.services.IMonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MonitoringSeviceImpl implements IMonitoringService {
    @Autowired
    IMonitoringDao monitoringDao;

    @Autowired
    IPersonDao personDao;
    @Autowired
    IExamDao examDao;

    @Override
    public void addMonitoring(Monitoring monitoring) {
        Exam exam = monitoring.getExam();
        if (exam != null) {
            Optional<Exam> optionalExam = examDao.findById(exam.getIdExam());

            if (optionalExam.isPresent()) {
                exam = optionalExam.get();
                Educationalelement element = exam.getElement();
                Professor coordinator = element.getCoordinator();
                monitoring.setCoordinator(coordinator);

                monitoringDao.save(monitoring);
            } else {
                throw new RuntimeException("Exam not found with ID: " + exam.getIdExam());
            }
        } else {
            throw new IllegalArgumentException("Exam cannot be null in the monitoring entity");
        }
    }


    @Override
    public void updateMonitoring(Long idMonitor, Monitoring monitoring) {
                monitoringDao.save(monitoring);


    }

    @Override
    public List<Monitoring> getAllMonitoring() {
        return monitoringDao.findAll();
    }

    @Override
    public void deleteMonitoring(Long id) {
        if (getMonitoringById(id) != null) {
            monitoringDao.deleteById(id);}
    }

    @Override
    public Monitoring getMonitoringById(Long id) {
        return monitoringDao.findById(id).get();
    }

    @Override
    public List<Monitoring> getMonitoringByDate(String dateExam) {
        return monitoringDao.findByDateExam(dateExam);
    }

    @Override
    public void addProfessorsToMonitoring(Long monitoringId, Set<Long> professorIds) {
        Monitoring monitoring = getMonitoringById(monitoringId);
        if (monitoring != null) {
            for (Long professorId : professorIds) {
                Optional<Person> personOptional = personDao.findById(professorId);
                if (personOptional.isPresent()) {
                    Person person = personOptional.get();
                    if ("Professor".equals(person.getType())) {
                        Professor professor = (Professor) person;
                        monitoring.getProfessors().add(professor);
                    } else {
                        throw new IllegalArgumentException("Person with id " + professorId + " is not a Professor");
                    }
                } else {
                    throw new IllegalArgumentException("Person with id " + professorId + " does not exist");
                }
            }
            monitoringDao.save(monitoring);
        } else {
            throw new IllegalArgumentException("Monitoring session with id " + monitoringId + " does not exist");
        }
    }









}
