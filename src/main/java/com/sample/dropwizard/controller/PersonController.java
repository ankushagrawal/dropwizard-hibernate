package com.sample.dropwizard.controller;

import com.codahale.metrics.annotation.Timed;
import com.sample.dropwizard.dao.PersonDao;
import com.sample.dropwizard.dto.SampleDto;
import com.sample.dropwizard.entity.Person;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by ankush.a on 23/03/17.
 */
@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
public class PersonController {

    private PersonDao personDao;

    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GET
    @Timed
    public void test() {
        List<Person> personList = new ArrayList<Person>();
        for(int i = 1;i<=1000;i++) {
            Person person = new Person();
            person.setName("a1");
            personList.add(person);

        }
        long startTime = System.currentTimeMillis();
        personDao.createBulk(personList);
        long endTime = System.currentTimeMillis();
        System.out.println("time taken for 100000 rows = "+(endTime-startTime));
    }
}
