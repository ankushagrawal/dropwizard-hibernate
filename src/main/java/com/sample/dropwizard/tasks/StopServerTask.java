package com.sample.dropwizard.tasks;

import com.google.common.collect.ImmutableMultimap;
import io.dropwizard.servlets.tasks.Task;

import java.io.PrintWriter;

/**
 * Created by ankush.a on 22/03/17.
 */
public class StopServerTask extends Task {

    private int timeoutSeconds;

    public StopServerTask(int timeoutSeconds) {
        super("shutdown");
        this.timeoutSeconds = timeoutSeconds;
    }

    @Override
    public void execute(ImmutableMultimap<String, String> parameters, PrintWriter output) throws Exception {
        Thread.sleep(timeoutSeconds * 1000);
        System.out.println("shutting down server.....");
        System.exit(0);
    }
}
