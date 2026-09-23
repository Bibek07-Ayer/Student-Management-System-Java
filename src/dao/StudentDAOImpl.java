package dao;

import model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    private List<Student> students = new ArrayList<>();

    @Override
    public void addStudent(Student student) {
        students.add(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return students;
    }

    @Override
    public Student getStudentById(int id) {

        for (Student student : students) {

            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    @Override
    public List<Student> getStudentsByName(String name) {

        List<Student> result = new ArrayList<>();

        for (Student student : students) {

            if (student.getName()
                    .toLowerCase()
                    .contains(name.toLowerCase())) {

                result.add(student);
            }
        }

        return result;
    }

    @Override
    public void updateStudent(Student student) {

        Student existingStudent =
                getStudentById(student.getId());

        if (existingStudent != null) {

            existingStudent.setName(
                    student.getName()
            );

            existingStudent.setEmail(
                    student.getEmail()
            );

            existingStudent.setPhone(
                    student.getPhone()
            );

            existingStudent.setAddress(
                    student.getAddress()
            );
        }
    }

    @Override
    public void deleteStudent(int id) {

        Student student =
                getStudentById(id);

        if (student != null) {
            students.remove(student);
        }
    }
}