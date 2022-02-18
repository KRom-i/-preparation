package app.service;

import app.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Optional<Student> getStudentById(Long studentId);

    List<Student> getAllStudents();

    Student saveStudent(Student student);

    void deleteStudent(Student student);

}
