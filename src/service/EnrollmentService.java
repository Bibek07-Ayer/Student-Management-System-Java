package service;

import dao.EnrollmentDAO;
import model.Enrollment;

import java.util.List;

public class EnrollmentService {

    private EnrollmentDAO enrollmentDAO;

    public EnrollmentService(EnrollmentDAO enrollmentDAO) {
        this.enrollmentDAO = enrollmentDAO;
    }

    public void enrollStudent(Enrollment enrollment) {
        enrollmentDAO.enrollStudent(enrollment);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentDAO.getAllEnrollments();
    }

    public void unenrollStudent(int studentId, int courseId) {
        enrollmentDAO.unenrollStudent(studentId, courseId);
    }

    public List<Enrollment> getEnrollmentsByStudent(int studentId) {
        return enrollmentDAO.getEnrollmentsByStudent(studentId);
    }

    public List<Enrollment> getEnrollmentsByCourse(int courseId) {
        return enrollmentDAO.getEnrollmentsByCourse(courseId);
    }
}