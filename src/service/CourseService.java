package service;

import dao.CourseDAO;
import exception.CourseNotFoundException;
import model.Course;

import java.util.List;

public class CourseService {

    private CourseDAO courseDAO;

    public CourseService(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public void addCourse(Course course) {
        courseDAO.addCourse(course);
    }

    public List<Course> getAllCourses() {
        return courseDAO.getAllCourses();
    }

    public Course getCourseById(int id)
            throws CourseNotFoundException {

        Course course = courseDAO.getCourseById(id);

        if (course == null) {
            throw new CourseNotFoundException(
                    "Course with ID " + id + " was not found."
            );
        }

        return course;
    }

    public void updateCourse(Course course)
            throws CourseNotFoundException {

        getCourseById(course.getId());

        courseDAO.updateCourse(course);
    }

    public void deleteCourse(int id)
            throws CourseNotFoundException {

        getCourseById(id);

        courseDAO.deleteCourse(id);
    }
}