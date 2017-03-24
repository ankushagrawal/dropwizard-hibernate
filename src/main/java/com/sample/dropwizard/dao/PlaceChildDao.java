package com.sample.dropwizard.dao;

import com.sample.dropwizard.entity.Child;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by ankush.a on 24/03/17.
 */
public class PlaceChildDao extends AbstractDAO<Child> {

    private SessionFactory sessionFactory;
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public PlaceChildDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void createBulk(List<Child> children) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        for ( int i=0; i<children.size(); i++ ) {
            Child p = children.get(i);
            session.persist(p);
            if ( i % 200 == 0 ) { //50, same as the JDBC batch size
                //flush a batch of inserts and release memory:
                session.flush();
                session.clear();
            }
        }
        tx.commit();
        session.close();
    }
}
