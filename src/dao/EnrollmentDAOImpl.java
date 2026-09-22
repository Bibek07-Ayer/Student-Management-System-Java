package dao;

import model.Enrollment;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAOImpl implements EnrollmentDAO {

    private List<Enrollment> enrollments = new ArrayList<>();

    @Override
    public void enrollStudent(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        return enrollments;
    }

    @Override
    public void unenrollStudent(int studentId, int courseId) {

        enrollments.removeIf(enrollment ->
                enrollment.getStudentId() == studentId
                        && enrollment.getCourseId() == courseId
        );
    }

    @Override
    public List<Enrollment> getEnrollmentsByStudent(int studentId) {

        List<Enrollment> result = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {

            if (enrollment.getStudentId() == studentId) {
                result.add(enrollment);
            }
        }

        return result;
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourse(int courseId) {

        List<Enrollment> result = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {

            if (enrollment.getCourseId() == courseId) {
                result.add(enrollment);
            }
        }

        return result;
    }
}