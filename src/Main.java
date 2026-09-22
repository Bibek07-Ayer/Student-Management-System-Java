import dao.StudentDAO;
import dao.StudentDAOImpl;
import exception.StudentNotFoundException;
import model.Student;
import service.StudentService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        StudentDAO studentDAO = new StudentDAOImpl();
        StudentService studentService = new StudentService(studentDAO);

        while (true) {

            System.out.println("\n=================================");
            System.out.println("   STUDENT MANAGEMENT SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addStudent(scanner, studentService);
                    break;

                case 2:
                    viewStudents(studentService);
                    break;

                case 3:
                    searchStudent(scanner, studentService);
                    break;

                case 4:
                    updateStudent(scanner, studentService);
                    break;

                case 5:
                    deleteStudent(scanner, studentService);
                    break;

                case 6:
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

        System.out.println("Student added successfully!");
    }

    // View Students
    public static void viewStudents(
            StudentService studentService) {

        if (studentService.getAllStudents().isEmpty()) {

            System.out.println("No students found.");
            return;
        }

        System.out.println("\n----- All Students -----");

        for (Student student : studentService.getAllStudents()) {

            student.displayInfo();

            System.out.println("------------------------");
        }
    }

    // Search Student
    public static void searchStudent(
            Scanner scanner,
            StudentService studentService) {

        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();

        try {

            Student student = studentService.getStudentById(id);

            System.out.println("\nStudent Found:");

            student.displayInfo();

        } catch (StudentNotFoundException e) {

            System.out.println(e.getMessage());
        }
    }

    // Update Student
    public static void updateStudent(
            Scanner scanner,
            StudentService studentService) {

        System.out.print("Enter student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {

            Student student = studentService.getStudentById(id);

            System.out.print("Enter new name: ");
            String name = scanner.nextLine();

            System.out.print("Enter new email: ");
            String email = scanner.nextLine();

            System.out.print("Enter new phone: ");
            String phone = scanner.nextLine();

            System.out.print("Enter new address: ");
            String address = scanner.nextLine();

            Student updatedStudent = new Student(
                    id,
                    name,
                    email,
                    phone,
                    address
            );

            studentService.updateStudent(updatedStudent);

            System.out.println(
                    "Student updated successfully!"
            );

        } catch (StudentNotFoundException e) {

            System.out.println(e.getMessage());
        }
    }

    // Delete Student
    public static void deleteStudent(
            Scanner scanner,
            StudentService studentService) {

        System.out.print("Enter student ID to delete: ");
        int id = scanner.nextInt();

        try {

            studentService.getStudentById(id);

            studentService.deleteStudent(id);

            System.out.println(
                    "Student deleted successfully!"
            );

        } catch (StudentNotFoundException e) {

            System.out.println(e.getMessage());
        }
    }
}