import dao.CourseDAO;
import dao.CourseDAOImpl;
import dao.EnrollmentDAO;
import dao.EnrollmentDAOImpl;
import dao.StudentDAO;
import dao.StudentDAOImpl;

import exception.CourseNotFoundException;
import exception.StudentNotFoundException;

import model.Course;
import model.Enrollment;
import model.Student;

import service.CourseService;
import service.EnrollmentService;
import service.StudentService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Student
        StudentDAO studentDAO = new StudentDAOImpl();
        StudentService studentService =
                new StudentService(studentDAO);

        // Course
        CourseDAO courseDAO = new CourseDAOImpl();
        CourseService courseService =
                new CourseService(courseDAO);

        // Enrollment
        EnrollmentDAO enrollmentDAO =
                new EnrollmentDAOImpl();

        EnrollmentService enrollmentService =
                new EnrollmentService(enrollmentDAO);

        while (true) {

            System.out.println("\n=================================");
            System.out.println("   STUDENT MANAGEMENT SYSTEM");
            System.out.println("=================================");

            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");

            System.out.println("6. Add Course");
            System.out.println("7. View All Courses");
            System.out.println("8. Update Course");
            System.out.println("9. Delete Course");

            System.out.println("10. Enroll Student");
            System.out.println("11. View Enrollments");
            System.out.println("12. Unenroll Student");

            System.out.println("13. Exit");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                // ================= STUDENT =================

                case 1:
                    addStudent(
                            scanner,
                            studentService
                    );
                    break;

                case 2:
                    viewStudents(studentService);
                    break;

                case 3:
                    searchStudent(
                            scanner,
                            studentService
                    );
                    break;

                case 4:
                    updateStudent(
                            scanner,
                            studentService
                    );
                    break;

                case 5:
                    deleteStudent(
                            scanner,
                            studentService
                    );
                    break;

                // ================= COURSE =================

                case 6:
                    addCourse(
                            scanner,
                            courseService
                    );
                    break;

                case 7:
                    viewCourses(courseService);
                    break;

                case 8:
                    updateCourse(
                            scanner,
                            courseService
                    );
                    break;

                case 9:
                    deleteCourse(
                            scanner,
                            courseService
                    );
                    break;

                // ================= ENROLLMENT =================

                case 10:
                    enrollStudent(
                            scanner,
                            enrollmentService,
                            studentService,
                            courseService
                    );
                    break;

                case 11:
                    viewEnrollments(
                            enrollmentService
                    );
                    break;

                case 12:
                    unenrollStudent(
                            scanner,
                            enrollmentService
                    );
                    break;

                // ================= EXIT =================

                case 13:

                    System.out.println(
                            "Thank you for using Student Management System!"
                    );

                    scanner.close();

                    return;

                default:

                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }
        }
    }


    // =====================================================
    // STUDENT METHODS
    // =====================================================

    // Add Student
    public static void addStudent(
            Scanner scanner,
            StudentService studentService) {

        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        Student student = new Student(
                id,
                name,
                email,
                phone,
                address
        );

        studentService.addStudent(student);

        System.out.println(
                "Student added successfully!"
        );
    }


    // View Students
    public static void viewStudents(
            StudentService studentService) {

        if (studentService.getAllStudents().isEmpty()) {

            System.out.println(
                    "No students found."
            );

            return;
        }

        System.out.println(
                "\n----- All Students -----"
        );

        for (Student student :
                studentService.getAllStudents()) {

            student.displayInfo();

            System.out.println(
                    "------------------------"
            );
        }
    }


    // Search Student
    public static void searchStudent(
            Scanner scanner,
            StudentService studentService) {

        System.out.print(
                "Enter student ID: "
        );

        int id = scanner.nextInt();

        try {

            Student student =
                    studentService.getStudentById(id);

            System.out.println(
                    "\nStudent Found:"
            );

            student.displayInfo();

        } catch (StudentNotFoundException e) {

            System.out.println(
                    e.getMessage()
            );
        }
    }


    // Update Student
    public static void updateStudent(
            Scanner scanner,
            StudentService studentService) {

        System.out.print(
                "Enter student ID to update: "
        );

        int id = scanner.nextInt();
        scanner.nextLine();

        try {

            studentService.getStudentById(id);

            System.out.print(
                    "Enter new name: "
            );

            String name =
                    scanner.nextLine();

            System.out.print(
                    "Enter new email: "
            );

            String email =
                    scanner.nextLine();

            System.out.print(
                    "Enter new phone: "
            );

            String phone =
                    scanner.nextLine();

            System.out.print(
                    "Enter new address: "
            );

            String address =
                    scanner.nextLine();

            Student updatedStudent =
                    new Student(
                            id,
                            name,
                            email,
                            phone,
                            address
                    );

            studentService.updateStudent(
                    updatedStudent
            );

            System.out.println(
                    "Student updated successfully!"
            );

        } catch (StudentNotFoundException e) {

            System.out.println(
                    e.getMessage()
            );
        }
    }


    // Delete Student
    public static void deleteStudent(
            Scanner scanner,
            StudentService studentService) {

        System.out.print(
                "Enter student ID to delete: "
        );

        int id = scanner.nextInt();

        try {

            studentService.getStudentById(id);

            studentService.deleteStudent(id);

            System.out.println(
                    "Student deleted successfully!"
            );

        } catch (StudentNotFoundException e) {

            System.out.println(
                    e.getMessage()
            );
        }
    }


    // =====================================================
    // COURSE METHODS
    // =====================================================

    // Add Course
    public static void addCourse(
            Scanner scanner,
            CourseService courseService) {

        System.out.print(
                "Enter course ID: "
        );

        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print(
                "Enter course code: "
        );

        String courseCode =
                scanner.nextLine();

        System.out.print(
                "Enter course name: "
        );

        String courseName =
                scanner.nextLine();

        Course course =
                new Course(
                        id,
                        courseCode,
                        courseName
                );

        courseService.addCourse(course);

        System.out.println(
                "Course added successfully!"
        );
    }


    // View Courses
    public static void viewCourses(
            CourseService courseService) {

        if (courseService.getAllCourses().isEmpty()) {

            System.out.println(
                    "No courses found."
            );

            return;
        }

        System.out.println(
                "\n----- All Courses -----"
        );

        for (Course course :
                courseService.getAllCourses()) {

            course.displayCourse();

            System.out.println(
                    "-----------------------"
            );
        }
    }


    // Update Course
    public static void updateCourse(
            Scanner scanner,
            CourseService courseService) {

        System.out.print(
                "Enter course ID to update: "
        );

        int id = scanner.nextInt();
        scanner.nextLine();

        try {

            courseService.getCourseById(id);

            System.out.print(
                    "Enter new course code: "
            );

            String courseCode =
                    scanner.nextLine();

            System.out.print(
                    "Enter new course name: "
            );

            String courseName =
                    scanner.nextLine();

            Course updatedCourse =
                    new Course(
                            id,
                            courseCode,
                            courseName
                    );

            courseService.updateCourse(
                    updatedCourse
            );

            System.out.println(
                    "Course updated successfully!"
            );

        } catch (CourseNotFoundException e) {

            System.out.println(
                    e.getMessage()
            );
        }
    }


    // Delete Course
    public static void deleteCourse(
            Scanner scanner,
            CourseService courseService) {

        System.out.print(
                "Enter course ID to delete: "
        );

        int id = scanner.nextInt();

        try {

            courseService.getCourseById(id);

            courseService.deleteCourse(id);

            System.out.println(
                    "Course deleted successfully!"
            );

        } catch (CourseNotFoundException e) {

            System.out.println(
                    e.getMessage()
            );
        }
    }


    // =====================================================
    // ENROLLMENT METHODS
    // =====================================================

    // Enroll Student
    public static void enrollStudent(
            Scanner scanner,
            EnrollmentService enrollmentService,
            StudentService studentService,
            CourseService courseService) {

        System.out.print(
                "Enter student ID: "
        );

        int studentId =
                scanner.nextInt();

        System.out.print(
                "Enter course ID: "
        );

        int courseId =
                scanner.nextInt();

        try {

            // Check student exists
            studentService.getStudentById(
                    studentId
            );

            // Check course exists
            courseService.getCourseById(
                    courseId
            );

            System.out.print(
                    "Enter enrollment ID: "
            );

            int enrollmentId =
                    scanner.nextInt();

            Enrollment enrollment =
                    new Enrollment(
                            enrollmentId,
                            studentId,
                            courseId
                    );

            enrollmentService.enrollStudent(
                    enrollment
            );

            System.out.println(
                    "Student enrolled successfully!"
            );

        } catch (
                StudentNotFoundException |
                CourseNotFoundException e) {

            System.out.println(
                    e.getMessage()
            );
        }
    }


    // View Enrollments
    public static void viewEnrollments(
            EnrollmentService enrollmentService) {

        if (enrollmentService
                .getAllEnrollments()
                .isEmpty()) {

            System.out.println(
                    "No enrollments found."
            );

            return;
        }

        System.out.println(
                "\n----- All Enrollments -----"
        );

        for (Enrollment enrollment :
                enrollmentService
                        .getAllEnrollments()) {

            System.out.println(
                    "Enrollment ID: "
                            + enrollment.getId()
            );

            System.out.println(
                    "Student ID: "
                            + enrollment.getStudentId()
            );

            System.out.println(
                    "Course ID: "
                            + enrollment.getCourseId()
            );

            System.out.println(
                    "---------------------------"
            );
        }
    }


    // Unenroll Student
    public static void unenrollStudent(
            Scanner scanner,
            EnrollmentService enrollmentService) {

        System.out.print(
                "Enter student ID: "
        );

        int studentId =
                scanner.nextInt();

        System.out.print(
                "Enter course ID: "
        );

        int courseId =
                scanner.nextInt();

        enrollmentService.unenrollStudent(
                studentId,
                courseId
        );

        System.out.println(
                "Student unenrolled successfully!"
        );
    }
}