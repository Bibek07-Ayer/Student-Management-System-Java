import dao.StudentDAO;
import dao.StudentDAOImpl;
import model.Student;

public class Main {

    public static void main(String[] args) {

        StudentDAO studentDAO = new StudentDAOImpl();

        Student student1 = new Student(
                1,
                "Bibek Ayer",
                "bibek@gmail.com",
                "9800000000",
                "Kathmandu"
        );

        studentDAO.addStudent(student1);

        System.out.println("Student added successfully!");

        System.out.println("\nAll Students:");

        for (Student student : studentDAO.getAllStudents()) {
            student.displayInfo();
        }
    }
}