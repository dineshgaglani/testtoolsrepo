package com.expedia.airawat.test.executor.service;


import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by dgaglani on 8/31/14.
 */
public class TestExecutorServiceApplication extends Application<TestExecutorDropwizardServiceConfiguration> {

    @Override
    public void initialize(Bootstrap<TestExecutorDropwizardServiceConfiguration> testExecutorDropwizardServiceConfigurationBootstrap) {

    }

    @Override
    public void run(TestExecutorDropwizardServiceConfiguration testExecutorDropwizardServiceConfiguration, Environment environment) throws Exception {
        ExecutorServiceResource executorServiceResource = new ExecutorServiceResource();
        environment.jersey().register(executorServiceResource);
    }

    public static void main(String args[]) throws Exception {
           new TestExecutorServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }
}
