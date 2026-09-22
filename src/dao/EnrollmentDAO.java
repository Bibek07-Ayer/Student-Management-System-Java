package dao;

import model.Enrollment;

import java.util.List;

public interface EnrollmentDAO {

    void enrollStudent(Enrollment enrollment);

    List<Enrollment> getAllEnrollments();

    void unenrollStudent(int studentId, int courseId);

    List<Enrollment> getEnrollmentsByStudent(int studentId);

    List<Enrollment> getEnrollmentsByCourse(int courseId);
}