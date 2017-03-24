package com.sample.dropwizard.controller;

import com.codahale.metrics.annotation.Timed;
import com.sample.dropwizard.dao.PlaceDao;
import com.sample.dropwizard.entity.Person;
import com.sample.dropwizard.entity.Place;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ankush.a on 24/03/17.
 */
@Path("/place")
@Produces(MediaType.APPLICATION_JSON)
public class PlaceController {
    private PlaceDao placeDao;

    public PlaceController(PlaceDao placeDao) {
        this.placeDao = placeDao;
    }

    @GET
    @Timed
    public void test() {
        List<Place> placeList = new ArrayList<Place>();
        for(int i = 1;i<=10000;i++) {
            Place place = new Place();
            place.setName("p"+i);
            placeList.add(place);

        }
        long startTime = System.currentTimeMillis();
        placeDao.createBulk(placeList);
        long endTime = System.currentTimeMillis();
        System.out.println("time taken for 100000 rows = " + (endTime - startTime));
    }
}
