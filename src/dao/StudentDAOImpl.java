package dao;

import exception.DatabaseException;
import model.Student;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public void addStudent(Student student) {

        String sql = "INSERT INTO students " +
                "(id, name, email, phone, address) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, student.getId());
            statement.setString(2, student.getName());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getPhone());
            statement.setString(5, student.getAddress());

            statement.executeUpdate();

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Could not add student.",
                    e
            );
        }
    }

    @Override
    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT id, name, email, phone, address " +
                "FROM students";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Student student = new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("address")
                );

                students.add(student);
            }

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Could not retrieve students.",
                    e
            );
        }

        return students;
    }

    @Override
    public Student getStudentById(int id) {

        String sql = "SELECT id, name, email, phone, address " +
                "FROM students WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {

                    return new Student(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getString("phone"),
                            resultSet.getString("address")
                    );
                }
            }

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Could not search for student.",
                    e
            );
        }

        return null;
    }

    @Override
    public List<Student> getStudentsByName(String name) {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT id, name, email, phone, address " +
                "FROM students WHERE LOWER(name) LIKE ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                    1,
                    "%" + name.toLowerCase() + "%"
            );

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                while (resultSet.next()) {

                    Student student = new Student(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getString("phone"),
                            resultSet.getString("address")
                    );

                    students.add(student);
                }
            }

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Could not search students by name.",
                    e
            );
        }

        return students;
    }

    @Override
    public void updateStudent(Student student) {

        String sql = "UPDATE students " +
                "SET name = ?, email = ?, phone = ?, address = ? " +
                "WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getPhone());
            statement.setString(4, student.getAddress());
            statement.setInt(5, student.getId());

            statement.executeUpdate();

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Could not update student.",
                    e
            );
        }
    }

    @Override
    public void deleteStudent(int id) {

        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Could not delete student.",
                    e
            );
        }
    }
}