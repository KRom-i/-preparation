package app;

import app.entity.Student;
import app.repository.StudentRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class StudentsApplication implements ApplicationRunner {

    private final StudentRepository studentRepository;

    public StudentsApplication (StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public static void main (String[] args) {
        SpringApplication.run (StudentsApplication.class, args);
    }

    @Override
    public void run (ApplicationArguments args) throws Exception {
        List<Student> students = new ArrayList<> ();
        for (long i = 0; i < 10; i++) {
            students.add (new Student (i + 1, "Student " + i, 25));
        }
        studentRepository.saveAll (students);
    }
}
