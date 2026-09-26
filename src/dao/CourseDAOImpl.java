package dao;

import exception.DatabaseException;
import model.Course;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    @Override
    public void addCourse(Course course) {
        String sql = "INSERT INTO courses (id, course_code, course_name) VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, course.getId());
            statement.setString(2, course.getCourseCode());
            statement.setString(3, course.getCourseName());

            int rows = statement.executeUpdate();

            System.out.println("Rows inserted: " + rows);
            System.out.println("Database: " + connection.getCatalog());

        } catch (SQLException e) {
            throw new DatabaseException("Could not add course.", e);
        }
    }

    @Override
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();

        String sql = "SELECT id, course_code, course_name FROM courses";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Course course = new Course(
                        resultSet.getInt("id"),
                        resultSet.getString("course_code"),
                        resultSet.getString("course_name")
                );

                courses.add(course);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Could not retrieve courses.", e);
        }

        return courses;
    }

    @Override
    public Course getCourseById(int id) {
        String sql = "SELECT id, course_code, course_name FROM courses WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Course(
                            resultSet.getInt("id"),
                            resultSet.getString("course_code"),
                            resultSet.getString("course_name")
                    );
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Could not search for course.", e);
        }

        return null;
    }

    @Override
    public void updateCourse(Course course) {
        String sql = "UPDATE courses SET course_code = ?, course_name = ? WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, course.getCourseCode());
            statement.setString(2, course.getCourseName());
            statement.setInt(3, course.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Could not update course.", e);
        }
    }

    @Override
    public void deleteCourse(int id) {
        String sql = "DELETE FROM courses WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Could not delete course.", e);
        }
    }
}