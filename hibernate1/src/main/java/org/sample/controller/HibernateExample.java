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
import org.sample.model.Address;
import org.sample.model.Meeting;
import org.sample.model.Student;
import org.sample.model.StudentDetail;

import java.util.HashSet;
import java.util.Set;

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
        Student s1 = new Student("jack", "Gopu", "A");
        Student s2 = new Student("srujan", "mosby", "B");

        //one to one mapping example
        StudentDetail studentDetail = new StudentDetail();
        studentDetail.setGrade("5");
        studentDetail.setNationality("Indian");
        studentDetail.setRace("Black");
        studentDetail.setStudent(s1);
        s1.setStudentDetail(studentDetail);


        //one to many example
        Address homeAddres = new Address();
        homeAddres.setStreetName("state1");
        homeAddres.setCity("NAshville");
        homeAddres.setState("TN");
        homeAddres.setZip("37221");
        homeAddres.setStudent(s1);

        Address workAddress = new Address();
        workAddress.setStreetName("plum grove");
        workAddress.setCity("Chicago");
        workAddress.setState("IL");
        workAddress.setZip("60169");
        workAddress.setStudent(s1);


        s1.getAddresses().add(homeAddres);
        s1.getAddresses().add(workAddress);

        //session.save(homeAddres);
        //session.save(workAddress);


        //many to many
        Meeting m1 = new Meeting("Java class", "101", "Jack");
        Meeting m2 = new Meeting("Angular class", "102", "Rob");
        m1.getStudents().add(s1);
        m2.getStudents().add(s1);
        m2.getStudents().add(s2);

        s1.getMeetings().add(m1);
        s1.getMeetings().add(m2);

        s2.getMeetings().add(m2);


        session.save(s1);
        session.save(s2);

       /* Student s1 = (Student) session.get(Student.class, new Integer(1));
        System.out.println(s1.getFirstName());
        Set<Address> addresses = s1.getAddresses();
       for(Address a1: addresses){
           System.out.println(a1.getState());
       }
*/

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
