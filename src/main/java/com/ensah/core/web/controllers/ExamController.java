package com.ensah.core.web.controllers;




import com.ensah.core.bo.Exam;
import com.ensah.core.services.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/Exam")
public class ExamController {

    @Autowired
    IExamService examService;

    @PostMapping()
    public ResponseEntity<Exam> addExamRS(@RequestBody Exam exam) {
        examService.addExam(exam);
        return ResponseEntity.noContent().build();
    }

}


