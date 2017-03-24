package com.sample.dropwizard.dao;

import com.sample.dropwizard.entity.Parent;
import com.sample.dropwizard.entity.Child;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by ankush.a on 24/03/17.
 */
public class ParentDao extends AbstractDAO<Parent> {

    private SessionFactory sessionFactory;
    private PlaceChildDao childDao;
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public ParentDao(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public ParentDao(SessionFactory sessionFactory, PlaceChildDao childDao) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
        this.childDao = childDao;
    }

    public void createBulk(List<Parent> places) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        for ( int i=0; i<places.size(); i++ ) {
            Parent p = places.get(i);
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

    public void createBulkParentChild(List<Parent> parentList) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        for ( int i=0; i<parentList.size(); i++ ) {
            Parent p = parentList.get(i);
            List<Child> children = p.getChildren();
            for(Child c: children) {
                c.setParentPlace(p);
            }
            session.save(p);

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
