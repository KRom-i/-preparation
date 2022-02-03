package repository;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Collection;

final class StudentRepositoryImpl implements StudentRepository {

    private final SessionFactory sessionFactory;

    public StudentRepositoryImpl (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Student getById (Long id) {
        Session session = getCurrentSession ();
        session.beginTransaction ();
        Student student = session.get (Student.class, id);
        session.getTransaction ().commit ();
        return student;
    }

    @Override
    public Collection<Student> findAll () {
        Session session = getCurrentSession ();
        session.beginTransaction ();
        Collection<Student> students = getCurrentSession ().createQuery ("from Student", Student.class).getResultList ();
        session.getTransaction ().commit ();
        return students;
    }

    @Override
    public Student save (Student student) {
        Session session = getCurrentSession ();
        session.beginTransaction ();
        session.save (student);
        session.getTransaction ().commit ();
        return student;
    }

    @Override
    public Collection<Student> save (Collection<Student> students) {
        Session session = getCurrentSession ();
        session.beginTransaction ();
        students.forEach (session::save);
        session.getTransaction ().commit ();
        return students;
    }

    @Override
    public Student update (Student student) {
        Session session = getCurrentSession ();
        session.beginTransaction ();
        session.update (student);
        session.getTransaction ().commit ();
        return student;
    }

    @Override
    public Student delete (Student student) {
        Session session = getCurrentSession ();
        session.beginTransaction ();
        session.delete (student);
        session.getTransaction ().commit ();
        return student;
    }

    private Session getCurrentSession () {
        return this.sessionFactory.getCurrentSession ();
    }

}
