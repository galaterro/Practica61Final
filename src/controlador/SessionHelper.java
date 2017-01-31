package controlador;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * Created by Galaterro
 */
public class SessionHelper {

    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return ourSessionFactory;
    }
}
