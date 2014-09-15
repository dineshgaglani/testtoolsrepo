package com.test.demo.service;


import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by dgaglani on 8/31/14.
 */
public class CrudServiceApplication extends Application<TestExecutorDropwizardServiceConfiguration> {

    @Override
    public void initialize(Bootstrap<TestExecutorDropwizardServiceConfiguration> testExecutorDropwizardServiceConfigurationBootstrap) {

    }

    @Override
    public void run(TestExecutorDropwizardServiceConfiguration testExecutorDropwizardServiceConfiguration, Environment environment) throws Exception {
        CrudServiceResource serviceResource = new CrudServiceResource();
        environment.jersey().register(serviceResource);
    }

    public static void main(String args[]) throws Exception {
           new CrudServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }
}
