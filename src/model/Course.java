package model;

public class Course {

    private int id;
    private String courseCode;
    private String courseName;

    public Course(int id, String courseCode, String courseName) {
        this.id = id;
        this.courseCode = courseCode;
        this.courseName = courseName;
    }

    public int getId() {
        return id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void displayCourse() {
        System.out.println("Course ID: " + id);
        System.out.println("Course Code: " + courseCode);
        System.out.println("Course Name: " + courseName);
    }
}