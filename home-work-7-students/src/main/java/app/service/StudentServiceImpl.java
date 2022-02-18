package app.service;

import app.entity.Student;
import app.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    StudentServiceImpl (StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<Student> getStudentById (Long studentId) {
        return studentRepository.findById (studentId);
    }

    @Override
    public List<Student> getAllStudents () {
        return studentRepository.findAll ();
    }

    @Override
    public Student saveStudent (Student student) {
        return studentRepository.save (student);
    }

    @Override
    public void deleteStudent (Student student) {
        studentRepository.delete (student);
    }
}
