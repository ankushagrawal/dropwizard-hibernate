package com.sample.dropwizard.controller;

import com.codahale.metrics.annotation.Timed;
import com.sample.dropwizard.dto.SampleDto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by ankush.a on 22/03/17.
 */
@Path("/sample")
@Produces(MediaType.APPLICATION_JSON)
public class SampleController {

    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public SampleController(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public SampleDto test(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.orElse(defaultName));
        return new SampleDto(counter.incrementAndGet(), value);
    }
}
