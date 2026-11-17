package com.robios;

import com.robios.entity.Route;
import com.robios.entity.Walks;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Dao {


    public void save(Walks walks) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(walks);
        tx.commit();
        session.close();

    }

    public void save(Route route) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();


        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(route);
        tx.commit();
        session.close();
    }


}
