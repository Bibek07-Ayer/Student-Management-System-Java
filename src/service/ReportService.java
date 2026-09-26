package service;

import model.Grade;
import model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService {

    public void printStudentReport(
            Student student,
            List<Grade> grades) {

        System.out.println();
        System.out.println("=================================");
        System.out.println("         STUDENT REPORT");
        System.out.println("=================================");

        System.out.println("Student ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Email: " + student.getEmail());

        System.out.println();
        System.out.println("----- Course Results -----");

        if (grades.isEmpty()) {

            System.out.println("No grades found.");

            return;
        }

        double total = 0;

        for (Grade grade : grades) {

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

            total += grade.getMarks();
        }

        double average =
                total / grades.size();

        System.out.println(
                "Average Marks: " + average
        );

        System.out.println("=================================");
    }

    public void printClassRanking(
            List<Student> students,
            List<Grade> grades) {

        System.out.println();
        System.out.println("=================================");
        System.out.println("          CLASS RANKING");
        System.out.println("=================================");

        if (students.isEmpty()) {

            System.out.println("No students found.");

            return;
        }

        /*
         * Map stores:
         * Student ID -> Average Marks
         */
        Map<Integer, Double> averages =
                new HashMap<>();

        for (Student student : students) {

            double average =
                    calculateAverage(
                            student.getId(),
                            grades
                    );

            averages.put(
                    student.getId(),
                    average
            );
        }

        /*
         * Create a list for sorting
         */
        List<Student> rankedStudents =
                new ArrayList<>(students);

        /*
         * Sort students by average marks
         * from highest to lowest
         */
        rankedStudents.sort(
                (student1, student2) -> {

                    double average1 =
                            averages.get(
                                    student1.getId()
                            );

                    double average2 =
                            averages.get(
                                    student2.getId()
                            );

                    return Double.compare(
                            average2,
                            average1
                    );
                }
        );

        int rank = 1;

        for (Student student :
                rankedStudents) {

            double average =
                    averages.get(
                            student.getId()
                    );

            System.out.println(
                    rank
                            + ". "
                            + student.getName()
                            + " - Average: "
                            + average
            );

            rank++;
        }

        System.out.println(
                "================================="
        );
    }

    private double calculateAverage(
            int studentId,
            List<Grade> grades) {

        double total = 0;
        int count = 0;

        for (Grade grade : grades) {

            if (grade.getStudentId() == studentId) {

                total += grade.getMarks();

                count++;
            }
        }

        if (count == 0) {

            return 0;
        }

        return total / count;
    }
}