package dao;

import exception.DatabaseException;
import model.Grade;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GradeDAOImpl implements GradeDAO {

    @Override
    public void addGrade(Grade grade) {

        String sql = "INSERT INTO grades " +
                "(id, student_id, course_id, marks) " +
                "VALUES (?, ?, ?, ?)";

        try {
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(1, grade.getId());
            statement.setInt(2, grade.getStudentId());
            statement.setInt(3, grade.getCourseId());
            statement.setDouble(4, grade.getMarks());

            int rows = statement.executeUpdate();

            System.out.println(
                    "Rows inserted: " + rows
            );

            statement.close();
            connection.close();

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Could not add grade.",
                    e
            );
        }
    }

    @Override
    public List<Grade> getAllGrades() {

        List<Grade> grades =
                new ArrayList<>();

        String sql =
                "SELECT id, student_id, course_id, marks " +
                        "FROM grades";

        try {
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                Grade grade =
                        new Grade(
                                resultSet.getInt("id"),
                                resultSet.getInt("student_id"),
                                resultSet.getInt("course_id"),
                                resultSet.getDouble("marks")
                        );

                grades.add(grade);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Could not retrieve grades.",
                    e
            );
        }

        return grades;
    }

    @Override
    public Grade getGradeById(int id) {

        String sql =
                "SELECT id, student_id, course_id, marks " +
                        "FROM grades " +
                        "WHERE id = ?";

        try {
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                Grade grade =
                        new Grade(
                                resultSet.getInt("id"),
                                resultSet.getInt("student_id"),
                                resultSet.getInt("course_id"),
                                resultSet.getDouble("marks")
                        );

                resultSet.close();
                statement.close();
                connection.close();

                return grade;
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Could not find grade.",
                    e
            );
        }

        return null;
    }

    @Override
    public void updateGrade(Grade grade) {

        String sql =
                "UPDATE grades " +
                        "SET marks = ? " +
                        "WHERE id = ?";

        try {
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setDouble(1, grade.getMarks());
            statement.setInt(2, grade.getId());

            int rows =
                    statement.executeUpdate();

            System.out.println(
                    "Rows updated: " + rows
            );

            statement.close();
            connection.close();

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Could not update grade.",
                    e
            );
        }
    }

    @Override
    public List<Grade> getGradesByStudent(
            int studentId) {

        List<Grade> grades =
                new ArrayList<>();

        String sql =
                "SELECT id, student_id, course_id, marks " +
                        "FROM grades " +
                        "WHERE student_id = ?";

        try {
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(1, studentId);

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                Grade grade =
                        new Grade(
                                resultSet.getInt("id"),
                                resultSet.getInt("student_id"),
                                resultSet.getInt("course_id"),
                                resultSet.getDouble("marks")
                        );

                grades.add(grade);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Could not retrieve student grades.",
                    e
            );
        }

        return grades;
    }

    @Override
    public List<Grade> getGradesByCourse(
            int courseId) {

        List<Grade> grades =
                new ArrayList<>();

        String sql =
                "SELECT id, student_id, course_id, marks " +
                        "FROM grades " +
                        "WHERE course_id = ?";

        try {
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(1, courseId);

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                Grade grade =
                        new Grade(
                                resultSet.getInt("id"),
                                resultSet.getInt("student_id"),
                                resultSet.getInt("course_id"),
                                resultSet.getDouble("marks")
                        );

                grades.add(grade);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Could not retrieve course grades.",
                    e
            );
        }

        return grades;
    }
}