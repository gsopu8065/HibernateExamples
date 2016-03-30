package org.sample.controller;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.sample.model.Student;

/**
 * Created by srujangopu on 3/27/16.
 */
public class HibernateExample {

    public static void  main(String arg[]) {

        //step1: get session factory
        SessionFactory sessionFactory = configureSessionFactory();

        //step2: create session
        Session session = sessionFactory.openSession();

        //step3: create transaction
        Transaction transaction = session.beginTransaction();

        //step4: update operation
        session.save(new Student("jack", "Gopu", "A"));

        //step5: commit transaction
        transaction.commit();

        //step6: close session and sessionFactory
        session.close();
        sessionFactory.close();
    }

    private static SessionFactory configureSessionFactory() throws HibernateException {
        Configuration configuration=new Configuration().configure(); // configures settings from hibernate.cfg.xml
        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
        // If you miss the below line then it will complaing about a missing dialect setting
        serviceRegistryBuilder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }
}
