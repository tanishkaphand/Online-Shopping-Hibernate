package com.Onlineshop.app;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    // Singleton SessionFactory instance
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Load settings from hibernate.cfg.xml (must be in src/main/resources)
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml"); // correct path
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("❌ Initial SessionFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Public method to return SessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // Shutdown hook
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
