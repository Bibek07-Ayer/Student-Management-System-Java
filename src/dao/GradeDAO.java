package dao;

import model.Grade;

import java.util.List;

public interface GradeDAO {

    void addGrade(Grade grade);

    List<Grade> getAllGrades();

    Grade getGradeById(int id);

    void updateGrade(Grade grade);

    List<Grade> getGradesByStudent(int studentId);

    List<Grade> getGradesByCourse(int courseId);
}