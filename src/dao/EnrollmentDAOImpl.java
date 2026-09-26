package dao;

import exception.DatabaseException;
import model.Enrollment;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAOImpl implements EnrollmentDAO {

    @Override
    public void enrollStudent(Enrollment enrollment) {

        String sql = "INSERT INTO enrollments " +
                "(id, student_id, course_id) VALUES (?, ?, ?)";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(1, enrollment.getId());
            statement.setInt(2, enrollment.getStudentId());
            statement.setInt(3, enrollment.getCourseId());

            statement.executeUpdate();

            statement.close();
            connection.close();

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Could not enroll student.",
                    e
            );
        }
    }

    @Override
    public List<Enrollment> getAllEnrollments() {

        List<Enrollment> enrollments =
                new ArrayList<>();

        String sql =
                "SELECT id, student_id, course_id " +
                        "FROM enrollments";

        try {
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                Enrollment enrollment =
                        new Enrollment(
                                resultSet.getInt("id"),
                                resultSet.getInt("student_id"),
                                resultSet.getInt("course_id")
                        );

                enrollments.add(enrollment);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Could not retrieve enrollments.",
                    e
            );
        }

        return enrollments;
    }

    @Override
    public void unenrollStudent(
            int studentId,
            int courseId) {

        String sql =
                "DELETE FROM enrollments " +
                        "WHERE student_id = ? " +
                        "AND course_id = ?";

        try {
            Connection connection =
                    DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(1, studentId);
            statement.setInt(2, courseId);

            statement.executeUpdate();

            statement.close();
            connection.close();

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Could not unenroll student.",
                    e
            );
        }
    }

    @Override
    public List<Enrollment> getEnrollmentsByStudent(
            int studentId) {

        List<Enrollment> enrollments =
                new ArrayList<>();

        String sql =
                "SELECT id, student_id, course_id " +
                        "FROM enrollments " +
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

                Enrollment enrollment =
                        new Enrollment(
                                resultSet.getInt("id"),
                                resultSet.getInt("student_id"),
                                resultSet.getInt("course_id")
                        );

                enrollments.add(enrollment);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Could not retrieve student enrollments.",
                    e
            );
        }

        return enrollments;
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourse(
            int courseId) {

        List<Enrollment> enrollments =
                new ArrayList<>();

        String sql =
                "SELECT id, student_id, course_id " +
                        "FROM enrollments " +
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

                Enrollment enrollment =
                        new Enrollment(
                                resultSet.getInt("id"),
                                resultSet.getInt("student_id"),
                                resultSet.getInt("course_id")
                        );

                enrollments.add(enrollment);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {

            throw new DatabaseException(
                    "Could not retrieve course enrollments.",
                    e
            );
        }

        return enrollments;
    }
}