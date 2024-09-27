package com.ensah.core.web.controllers;

import com.ensah.core.bo.Group;
import com.ensah.core.bo.Monitoring;
import com.ensah.core.services.Impl.MonitoringSeviceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequiredArgsConstructor
@Transactional
@RequestMapping("api/monitorings")
public class MonitoringController {
    @Autowired
    private  MonitoringSeviceImpl monitoringService;



    @PostMapping()
    public ResponseEntity<Monitoring> addMonitoringRS(@RequestBody Monitoring monitoring) {
        monitoringService.addMonitoring(monitoring);
        return ResponseEntity.noContent().build();
    }



    @GetMapping("/{dateExam}")
    public ResponseEntity<List<Monitoring>> getMonitoringByDate(@PathVariable String dateExam) {
        System.out.println("date exam is "+ dateExam);
        return ResponseEntity.ok(monitoringService.getMonitoringByDate(dateExam));

    }

    @PutMapping("/{monitoringId}/addProfessorsToMonitoring")
    public ResponseEntity<Monitoring>addProfessorsToMonitoring(@PathVariable Long monitoringId, @RequestBody Set<Long> professorIds) {
        monitoringService.addProfessorsToMonitoring(monitoringId, professorIds);
        return new ResponseEntity(HttpStatus.OK);
    }

}
