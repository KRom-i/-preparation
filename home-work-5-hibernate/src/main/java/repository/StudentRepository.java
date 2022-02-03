package repository;

import entity.Student;

import java.util.Collection;

public interface StudentRepository {

    Student getById (Long id);

    Collection<Student> findAll ();

    Student save (Student student);

    Collection<Student> save(Collection<Student> students);

    Student update (Student student);

    Student delete (Student student);
}
