package com.shahbaz;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Shahbaz on 4/1/2018.
 */
public class HibernateUtil {
    @Autowired
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml

            Configuration cfg = new Configuration();
//            cfg.configure();
            cfg.configure("hibernate.cfg.xml"); //hibernate config xml file name
            String newUserName,newPassword;//set them as per your needs

            cfg.getProperties().setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver");
            cfg.getProperties().setProperty("hibernate.connection.url","jdbc:mysql://localhost:3306/demodb?useSSL=false");
            cfg.getProperties().setProperty("hibernate.connection.username","root");
            cfg.getProperties().setProperty("hibernate.connection.password","asdfghjkl");
            cfg.getProperties().setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
            cfg.getProperties().setProperty("hibernate.current_session_context_class","thread");
            cfg.getProperties().setProperty("useSSL", "false");
//            cfg.addResource("com.hibernate.User");
            cfg.addPackage("com.hibernate");
//            cfg.addClass(User.class);
//            cfg.addClass(UserDetails.class);
            cfg.addResource("UserDetails.hbm.xml");
            cfg.addResource("User.hbm.xml");
//            cfg.addAnnotatedClass(User.class);
//            cfg.addAnnotatedClass(UserDetails.class);

            return cfg.configure().buildSessionFactory();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
