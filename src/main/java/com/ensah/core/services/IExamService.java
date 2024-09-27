package com.ensah.core.services;


import com.ensah.core.bo.Exam;

import java.util.List;

public interface IExamService {

    public void addExam(Exam exam);

    public void updateExam(Long examId,Exam exam);

    public List<Exam> getAllExams();

    public void deleteExam(Long id);

    public Exam getExamById(Long id);






}
