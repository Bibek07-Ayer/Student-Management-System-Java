CREATE DATABASE IF NOT EXISTS student_management;

USE student_management;

CREATE TABLE IF NOT EXISTS students (
                                        id INT PRIMARY KEY,
                                        name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(20),
    address VARCHAR(200)
    );

CREATE TABLE IF NOT EXISTS courses (
                                       id INT PRIMARY KEY,
                                       course_code VARCHAR(20) NOT NULL,
    course_name VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS enrollments (
                                           id INT PRIMARY KEY,
                                           student_id INT NOT NULL,
                                           course_id INT NOT NULL,

                                           FOREIGN KEY (student_id)
    REFERENCES students(id),

    FOREIGN KEY (course_id)
    REFERENCES courses(id)
    );

CREATE TABLE IF NOT EXISTS grades (
                                      id INT PRIMARY KEY,
                                      student_id INT NOT NULL,
                                      course_id INT NOT NULL,
                                      marks DOUBLE NOT NULL,

                                      FOREIGN KEY (student_id)
    REFERENCES students(id),

    FOREIGN KEY (course_id)
    REFERENCES courses(id)
    );