package dao;

import model.Grade;

import java.util.ArrayList;
import java.util.List;

public class GradeDAOImpl implements GradeDAO {

    private List<Grade> grades = new ArrayList<>();

    @Override
    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    @Override
    public List<Grade> getAllGrades() {
        return grades;
    }

    @Override
    public Grade getGradeById(int id) {

        for (Grade grade : grades) {

            if (grade.getId() == id) {
                return grade;
            }
        }

        return null;
    }

    @Override
    public void updateGrade(Grade grade) {

        Grade existingGrade =
                getGradeById(grade.getId());

        if (existingGrade != null) {
            existingGrade.setMarks(
                    grade.getMarks()
            );
        }
    }

    @Override
    public List<Grade> getGradesByStudent(int studentId) {

        List<Grade> result = new ArrayList<>();

        for (Grade grade : grades) {

            if (grade.getStudentId() == studentId) {
                result.add(grade);
            }
        }

        return result;
    }

    @Override
    public List<Grade> getGradesByCourse(int courseId) {

        List<Grade> result = new ArrayList<>();

        for (Grade grade : grades) {

            if (grade.getCourseId() == courseId) {
                result.add(grade);
            }
        }

        return result;
    }
}