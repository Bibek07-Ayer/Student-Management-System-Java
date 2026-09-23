package service;

import dao.GradeDAO;
import model.Grade;

import java.util.List;

public class GradeService {

    private GradeDAO gradeDAO;

    public GradeService(GradeDAO gradeDAO) {
        this.gradeDAO = gradeDAO;
    }

    public void addGrade(Grade grade) {
        gradeDAO.addGrade(grade);
    }

    public List<Grade> getAllGrades() {
        return gradeDAO.getAllGrades();
    }

    public Grade getGradeById(int id) {
        return gradeDAO.getGradeById(id);
    }

    public void updateGrade(Grade grade) {
        gradeDAO.updateGrade(grade);
    }

    public List<Grade> getGradesByStudent(int studentId) {
        return gradeDAO.getGradesByStudent(studentId);
    }

    public List<Grade> getGradesByCourse(int courseId) {
        return gradeDAO.getGradesByCourse(courseId);
    }

    public double calculateAverage(int studentId) {

        List<Grade> grades =
                gradeDAO.getGradesByStudent(studentId);

        if (grades.isEmpty()) {
            return 0;
        }

        double total = 0;

        for (Grade grade : grades) {
            total += grade.getMarks();
        }

        return total / grades.size();
    }
}