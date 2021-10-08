package com.example.liquibasedemo;


import com.example.liquibasedemo.dao.StudentRepository;
import com.example.liquibasedemo.entity.*;
import com.example.liquibasedemo.service.impl.StudentServiceImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


@SpringBootApplication
public class FlywayDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlywayDemoApplication.class, args);
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Attachment.class)
                .addAnnotatedClass(Chat.class)
                .addAnnotatedClass(Message.class)
                .addAnnotatedClass(Post.class)
                .addAnnotatedClass(School.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = null;

        School school = new School("Адрес");
        Student student1 = new Student("Змей","Горыныч",
                new GregorianCalendar(2000, Calendar.JANUARY, 1).getTime(), 10, 1, school);
        Student student2 = new Student("Добрыня","Никитич",
                new GregorianCalendar(2000, Calendar.JANUARY, 1).getTime(), 10, 1, school);
        student1.setFriendList(new ArrayList<>());
        student1.getFriendList().add(student2);
        //Post post = new Post(student);
        //Message message = new Message(student, chat);


        try{
            session = factory.getCurrentSession();
            session.beginTransaction();
            Student student3 = session.get(Student.class, 26L);
            //Student student4 = session.get(Student.class, 19L);
            //session.save(school);
            /*session.save(student);
            session.save(post);
            session.save(chat);
            session.save(message);*/
            //session.save(student1);
            //session.save(chat);
            session.delete(student3);
            session.getTransaction().commit();
        }
        finally {
            factory.close();
        }

    }

}
