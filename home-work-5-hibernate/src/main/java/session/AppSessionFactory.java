package session;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import static java.util.Objects.isNull;

public class AppSessionFactory {

    private static final String CONFIG_FILE_NAME = "hibernate.cfg.xml";

    private static SessionFactory sessionFactory;

    public static SessionFactory createSessionFactory () {

        if (isNull (sessionFactory)) {
            sessionFactory = new Configuration ()
                    .configure (CONFIG_FILE_NAME)
                    .addAnnotatedClass (Student.class)
                    .buildSessionFactory ();
        }

        return sessionFactory;
    }

    public static void closeSessionFactory(){
        if (isNull (sessionFactory)){
            return;
        }
        sessionFactory.close ();
    }
}
