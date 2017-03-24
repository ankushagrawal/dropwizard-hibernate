package com.sample.dropwizard;


import com.sample.dropwizard.controller.PersonController;
import com.sample.dropwizard.controller.PlaceController;
import com.sample.dropwizard.controller.SampleController;
import com.sample.dropwizard.dao.PersonDao;
import com.sample.dropwizard.dao.PlaceDao;
import com.sample.dropwizard.entity.Person;
import com.sample.dropwizard.entity.Place;
import com.sample.dropwizard.tasks.StopServerTask;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by ankush.a on 22/03/17.
 */
public class SampleApplication extends Application<SampleConfiguration> {

    @Override
    public String getName() {
        return "Sample application";
    }

    public static void main(String[] args) throws Exception {
        new SampleApplication().run(args);
    }

    private final HibernateBundle<SampleConfiguration> hibernate =
            new HibernateBundle<SampleConfiguration>(Person.class, Place.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(SampleConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<SampleConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(SampleConfiguration configuration, Environment environment) throws Exception {
        final SampleController controller = new SampleController(configuration.getTemplate(),configuration.getDefaultName());

        final ApplicationHealthCheck healthCheck = new ApplicationHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);

        environment.admin().addTask(new StopServerTask(1));

        final PersonDao personDao = new PersonDao(hibernate.getSessionFactory());
        final PlaceDao placeDao = new PlaceDao(hibernate.getSessionFactory());

        environment.jersey().register(controller);
        environment.jersey().register(new PersonController(personDao));
        environment.jersey().register(new PlaceController(placeDao));
    }


}
