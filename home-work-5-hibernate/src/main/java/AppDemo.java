import entity.Student;
import repository.StudentRepository;
import repository.StudentRepositoryFactory;
import session.AppSessionFactory;

import java.util.ArrayList;
import java.util.List;

public class AppDemo {

    public static void main (String[] args) {

        StudentRepository studentRepository = StudentRepositoryFactory.createStudentRepository (
                AppSessionFactory.createSessionFactory ()
        );

        // Save all
        List<Student> students = new ArrayList<> ();
        for (int i = 0; i < 1000; i++) {
            students.add (new Student ("Student " + (i + 1), 'B'));
        }
        studentRepository.save (students);

        // Get by id
        Student student = studentRepository.getById (1L);

        // Update
        student.setMark ('A');
        studentRepository.update (student);

        // Delete
        studentRepository.delete (student);

        // Find all
        studentRepository.findAll ();

        AppSessionFactory.closeSessionFactory ();
    }
}
