package repository;

import org.hibernate.SessionFactory;

public class StudentRepositoryFactory {

    public static StudentRepository createStudentRepository (SessionFactory sessionFactory) {
        return new StudentRepositoryImpl (sessionFactory);
    }
}
