package service;

import dao.StudentDAO;
import exception.StudentNotFoundException;
import model.Student;

import java.util.List;

public class StudentService {

    private StudentDAO studentDAO;

    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void addStudent(Student student) {
        studentDAO.addStudent(student);
    }

    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    public Student getStudentById(int id) throws StudentNotFoundException {

        Student student = studentDAO.getStudentById(id);

        if (student == null) {
            throw new StudentNotFoundException(
                    "Student with ID " + id + " was not found."
            );
        }

        return student;
    }

    public void updateStudent(Student student) {
        studentDAO.updateStudent(student);
    }

    public void deleteStudent(int id) {
        studentDAO.deleteStudent(id);
    }
}