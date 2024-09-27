package com.ensah.core.services.Impl;

import com.ensah.core.bo.Exam;
import com.ensah.core.bo.ExamType;
import com.ensah.core.bo.Semester;
import com.ensah.core.bo.Session;
import com.ensah.core.dao.IExamDao;
import com.ensah.core.services.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
@Transactional
public class ExamServiceImpl implements IExamService {

    @Autowired
    IExamDao examDao;

    @Override
    public void addExam(Exam exam) {
        if (exam.getDuration() == null || exam.getDuration().isEmpty()) {
            exam.setDuration("2h");
        }

        if (exam.getSemester() == null) {
            LocalDate currentDate = LocalDate.now();
            int month = currentDate.getMonthValue();
            exam.setSemester(month >= 9 || month <= 1 ? Semester.AUTOMNE : Semester.PRINTEMPS);
        }

        if (exam.getSession() == null) {
            exam.setSession(Session.NORMAL);
        }

        // Set default type if not provided
        if (exam.getExamType() == null) {
            LocalDate currentDate = LocalDate.now();
            int month = currentDate.getMonthValue();
            exam.setExamType(month == 11 ? ExamType.DS : month == 1 ? ExamType.EXAM : ExamType.DS);
        }

        examDao.save(exam);
    }



    @Override
    public void updateExam(Long examId, Exam exam) {
                examDao.save(exam);

    }

    @Override
    public List<Exam> getAllExams() {
        return examDao.findAll();
    }

    @Override
    public void deleteExam(Long id) {
        if (getExamById(id) != null) {
            examDao.deleteById(id);
        }

    }

    @Override
    public Exam getExamById(Long id) {
        return examDao.findById(id).get();
    }


    //TO _ DO
//    public void assignRoomsToExam(Long examId, List<RoomAssignment> roomAssignments) {
//        Exam exam = examDao.findById(examId).orElseThrow(() -> new ResourceNotFoundException("Exam not found"));
//
//    }


}
