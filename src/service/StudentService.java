package service;

import dao.StudentDAO;
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

    public Student getStudentById(int id) {
        return studentDAO.getStudentById(id);
    }

    public void updateStudent(Student student) {
        studentDAO.updateStudent(student);
    }

    public void deleteStudent(int id) {
        studentDAO.deleteStudent(id);
    }
}