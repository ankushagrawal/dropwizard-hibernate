package com.sample.dropwizard.controller;

import com.codahale.metrics.annotation.Timed;
import com.sample.dropwizard.annotations.MyCustomAnnotation;
import com.sample.dropwizard.dao.ParentDao;
import com.sample.dropwizard.entity.Parent;
import com.sample.dropwizard.entity.Child;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ankush.a on 24/03/17.
 */
@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
public class ParentController {
    private ParentDao parentDao;

    public ParentController(ParentDao parentDao) {
        this.parentDao = parentDao;
    }

//    @GET
//    @Timed
//    public void test() {
//        List<Parent> placeList = new ArrayList<Parent>();
//        for(int i = 1;i<=10000;i++) {
//            Parent place = new Parent();
//            place.setName("p"+i);
//            placeList.add(place);
//
//        }
//        long startTime = System.currentTimeMillis();
//        parentDao.createBulk(placeList);
//        long endTime = System.currentTimeMillis();
//        System.out.println("time taken for 100000 rows = " + (endTime - startTime));
//    }

    @GET
    @Timed
    @MyCustomAnnotation(studentName = "annotate1",stuAddress = "dfdf")
    public void test() {
        Class clazz = ParentController.class;
        for(Method method : clazz.getMethods()){
            MyCustomAnnotation annotation = (MyCustomAnnotation)method.getAnnotation(MyCustomAnnotation.class);
            if(annotation!=null){
                System.out.println("stu address " +annotation.stuAddress());
                System.out.println("stu name " + annotation.studentName());
                System.out.println("stu stream " +annotation.stuStream());
            }
        }
        List<Parent> parents = new ArrayList<Parent>();
        for(int i = 1;i<=10000;i++) {
            Parent place = new Parent();
            place.setName("parent"+i);
            List<Child> children = new ArrayList<Child>();
            for(int j =1;j<=2;j++){
                Child child = new Child();
                child.setName("parent"+i);
                children.add(child);

            }
            place.setChildren(children);
            parents.add(place);

        }
        long startTime = System.currentTimeMillis();
        parentDao.createBulkParentChild(parents);
        long endTime = System.currentTimeMillis();
        System.out.println("time taken for 10000 rows = " + (endTime - startTime));
    }
}
