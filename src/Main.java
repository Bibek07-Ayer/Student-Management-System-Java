import dao.CourseDAO;
import dao.CourseDAOImpl;
import dao.EnrollmentDAO;
import dao.EnrollmentDAOImpl;
import dao.GradeDAO;
import dao.GradeDAOImpl;
import dao.StudentDAO;
import dao.StudentDAOImpl;

import exception.CourseNotFoundException;
import exception.StudentNotFoundException;

import model.Course;
import model.Enrollment;
import model.Grade;
import model.Student;

import service.CourseService;
import service.EnrollmentService;
import service.GradeService;
import service.ReportService;
import service.StudentService;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        StudentDAO studentDAO = new StudentDAOImpl();
        StudentService studentService =
                new StudentService(studentDAO);

        CourseDAO courseDAO = new CourseDAOImpl();
        CourseService courseService =
                new CourseService(courseDAO);

        EnrollmentDAO enrollmentDAO =
                new EnrollmentDAOImpl();

        EnrollmentService enrollmentService =
                new EnrollmentService(enrollmentDAO);

        GradeDAO gradeDAO =
                new GradeDAOImpl();

        GradeService gradeService =
                new GradeService(gradeDAO);

        ReportService reportService =
                new ReportService();

        while (true) {

            System.out.println("\n=================================");
            System.out.println("   STUDENT MANAGEMENT SYSTEM");
            System.out.println("=================================");

            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Search Student by Name");
            System.out.println("5. Update Student");
            System.out.println("6. Delete Student");

            System.out.println("7. Add Course");
            System.out.println("8. View All Courses");
            System.out.println("9. Update Course");
            System.out.println("10. Delete Course");

            System.out.println("11. Enroll Student");
            System.out.println("12. View Enrollments");
            System.out.println("13. Unenroll Student");
            System.out.println("14. List Students by Course");

            System.out.println("15. Add/Record Marks");
            System.out.println("16. View Student Grades");
            System.out.println("17. Update Marks");
            System.out.println("18. View Student Average");

            System.out.println("19. View Student Report");

            System.out.println("20. Exit");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

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
                    searchStudentByName(
                            scanner,
                            studentService
                    );
                    break;

                case 5:
                    updateStudent(
                            scanner,
                            studentService
                    );
                    break;

                case 6:
                    deleteStudent(
                            scanner,
                            studentService
                    );
                    break;

                case 7:
                    addCourse(
                            scanner,
                            courseService
                    );
                    break;

                case 8:
                    viewCourses(courseService);
                    break;

                case 9:
                    updateCourse(
                            scanner,
                            courseService
                    );
                    break;

                case 10:
                    deleteCourse(
                            scanner,
                            courseService
                    );
                    break;

                case 11:
                    enrollStudent(
                            scanner,
                            enrollmentService,
                            studentService,
                            courseService
                    );
                    break;

                case 12:
                    viewEnrollments(
                            enrollmentService
                    );
                    break;

                case 13:
                    unenrollStudent(
                            scanner,
                            enrollmentService
                    );
                    break;

                case 14:
                    listStudentsByCourse(
                            scanner,
                            enrollmentService,
                            studentService
                    );
                    break;

                case 15:
                    addGrade(
                            scanner,
                            gradeService,
                            studentService,
                            courseService
                    );
                    break;

                case 16:
                    viewStudentGrades(
                            scanner,
                            gradeService
                    );
                    break;

                case 17:
                    updateGrade(
                            scanner,
                            gradeService
                    );
                    break;

                case 18:
                    viewStudentAverage(
                            scanner,
                            gradeService
                    );
                    break;

                case 19:
                    viewStudentReport(
                            scanner,
                            studentService,
                            gradeService,
                            reportService
                    );
                    break;

                case 20:

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


    public static void searchStudentByName(
            Scanner scanner,
            StudentService studentService) {

        System.out.print(
                "Enter student name: "
        );

        String name = scanner.nextLine();

        List<Student> students =
                studentService.getStudentsByName(name);

        if (students.isEmpty()) {

            System.out.println(
                    "No students found with that name."
            );

            return;
        }

        System.out.println(
                "\n----- Search Results -----"
        );

        for (Student student : students) {

            student.displayInfo();

            System.out.println(
                    "--------------------------"
            );
        }
    }


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

            studentService.getStudentById(
                    studentId
            );

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


    // =====================================================
    // LIST STUDENTS BY COURSE
    // =====================================================

    public static void listStudentsByCourse(
            Scanner scanner,
            EnrollmentService enrollmentService,
            StudentService studentService) {

        System.out.print(
                "Enter course ID: "
        );

        int courseId =
                scanner.nextInt();

        List<Enrollment> enrollments =
                enrollmentService
                        .getEnrollmentsByCourse(courseId);

        if (enrollments.isEmpty()) {

            System.out.println(
                    "No students enrolled in this course."
            );

            return;
        }

        System.out.println(
                "\n----- Students in Course "
                        + courseId
                        + " -----"
        );

        for (Enrollment enrollment :
                enrollments) {

            try {

                Student student =
                        studentService.getStudentById(
                                enrollment.getStudentId()
                        );

                System.out.println(
                        "Student ID: "
                                + student.getId()
                );

                System.out.println(
                        "Name: "
                                + student.getName()
                );

                System.out.println(
                        "Email: "
                                + student.getEmail()
                );

                System.out.println(
                        "-------------------------"
                );

            } catch (StudentNotFoundException e) {

                System.out.println(
                        e.getMessage()
                );
            }
        }
    }


    // =====================================================
    // GRADE METHODS
    // =====================================================

    public static void addGrade(
            Scanner scanner,
            GradeService gradeService,
            StudentService studentService,
            CourseService courseService) {

        System.out.print(
                "Enter grade ID: "
        );

        int id = scanner.nextInt();

        System.out.print(
                "Enter student ID: "
        );

        int studentId = scanner.nextInt();

        System.out.print(
                "Enter course ID: "
        );

        int courseId = scanner.nextInt();

        System.out.print(
                "Enter marks: "
        );

        double marks = scanner.nextDouble();

        try {

            studentService.getStudentById(
                    studentId
            );

            courseService.getCourseById(
                    courseId
            );

            Grade grade =
                    new Grade(
                            id,
                            studentId,
                            courseId,
                            marks
                    );

            gradeService.addGrade(grade);

            System.out.println(
                    "Marks recorded successfully!"
            );

            System.out.println(
                    "Grade: " + grade.getGrade()
            );

        } catch (
                StudentNotFoundException |
                CourseNotFoundException e) {

            System.out.println(
                    e.getMessage()
            );
        }
    }


    public static void viewStudentGrades(
            Scanner scanner,
            GradeService gradeService) {

        System.out.print(
                "Enter student ID: "
        );

        int studentId =
                scanner.nextInt();

        if (gradeService
                .getGradesByStudent(studentId)
                .isEmpty()) {

            System.out.println(
                    "No grades found for this student."
            );

            return;
        }

        System.out.println(
                "\n----- Student Grades -----"
        );

        for (Grade grade :
                gradeService
                        .getGradesByStudent(studentId)) {

            System.out.println(
                    "Grade ID: " + grade.getId()
            );

            System.out.println(
                    "Course ID: " + grade.getCourseId()
            );

            System.out.println(
                    "Marks: " + grade.getMarks()
            );

            System.out.println(
                    "Grade: " + grade.getGrade()
            );

            System.out.println(
                    "--------------------------"
            );
        }
    }


    public static void updateGrade(
            Scanner scanner,
            GradeService gradeService) {

        System.out.print(
                "Enter grade ID to update: "
        );

        int id =
                scanner.nextInt();

        Grade existingGrade =
                gradeService.getGradeById(id);

        if (existingGrade == null) {

            System.out.println(
                    "Grade not found."
            );

            return;
        }

        System.out.print(
                "Enter new marks: "
        );

        double marks =
                scanner.nextDouble();

        Grade updatedGrade =
                new Grade(
                        id,
                        existingGrade.getStudentId(),
                        existingGrade.getCourseId(),
                        marks
                );

        gradeService.updateGrade(
                updatedGrade
        );

        System.out.println(
                "Marks updated successfully!"
        );

        System.out.println(
                "New Grade: "
                        + updatedGrade.getGrade()
        );
    }


    public static void viewStudentAverage(
            Scanner scanner,
            GradeService gradeService) {

        System.out.print(
                "Enter student ID: "
        );

        int studentId =
                scanner.nextInt();

        double average =
                gradeService.calculateAverage(
                        studentId
                );

        if (average == 0) {

            System.out.println(
                    "No grades found for this student."
            );

            return;
        }

        System.out.println(
                "Average Marks: " + average
        );
    }


    // =====================================================
    // STUDENT REPORT
    // =====================================================

    public static void viewStudentReport(
            Scanner scanner,
            StudentService studentService,
            GradeService gradeService,
            ReportService reportService) {

        System.out.print(
                "Enter student ID: "
        );

        int studentId =
                scanner.nextInt();

        try {

            Student student =
                    studentService.getStudentById(
                            studentId
                    );

            List<Grade> grades =
                    gradeService.getGradesByStudent(
                            studentId
                    );

            reportService.printStudentReport(
                    student,
                    grades
            );

        } catch (StudentNotFoundException e) {

            System.out.println(
                    e.getMessage()
            );
        }
    }
}