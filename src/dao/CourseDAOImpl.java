package dao;

import model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    private List<Course> courses = new ArrayList<>();

    @Override
    public void addCourse(Course course) {
        courses.add(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courses;
    }

    @Override
    public Course getCourseById(int id) {

        for (Course course : courses) {

            if (course.getId() == id) {
                return course;
            }
        }

        return null;
    }

    @Override
    public void updateCourse(Course course) {

        Course existingCourse = getCourseById(course.getId());

        if (existingCourse != null) {

            existingCourse.setCourseCode(
                    course.getCourseCode()
            );

            existingCourse.setCourseName(
                    course.getCourseName()
            );
        }
    }

    @Override
    public void deleteCourse(int id) {

        Course course = getCourseById(id);

        if (course != null) {
            courses.remove(course);
        }
    }
}