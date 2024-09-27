package com.ensah.core.services;

import com.ensah.core.bo.Monitoring;

import java.util.List;
import java.util.Set;

public interface IMonitoringService {
    public void addMonitoring(Monitoring monitoring);

    public void updateMonitoring(Long idMonitor,Monitoring monitoring);

    public List<Monitoring> getAllMonitoring();

    public void deleteMonitoring(Long id);
    public Monitoring getMonitoringById(Long id);


    public List<Monitoring> getMonitoringByDate(String dateExam);

    public void addProfessorsToMonitoring(Long monitoringId, Set<Long> professorIds);
}
