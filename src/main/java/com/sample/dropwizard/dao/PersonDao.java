package com.sample.dropwizard.dao;

import com.sample.dropwizard.entity.Person;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by ankush.a on 23/03/17.
 */
public class PersonDao extends AbstractDAO<Person> {
    private SessionFactory sessionFactory;
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public PersonDao(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public Person findById(Long id) {
        return get(id);
    }

    public long create(Person person) {
        return persist(person).getId();
    }

    public void createBulk(List<Person> person) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        for ( int i=0; i<person.size(); i++ ) {
            Person p = person.get(i);
            session.persist(p);
            if ( i % 500 == 0 ) { //50, same as the JDBC batch size
                //flush a batch of inserts and release memory:
                session.flush();
                session.clear();
            }
        }
        tx.commit();
        session.close();
    }
}
