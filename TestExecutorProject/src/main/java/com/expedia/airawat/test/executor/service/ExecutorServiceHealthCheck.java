package com.expedia.airawat.test.executor.service;

import com.codahale.metrics.health.HealthCheck;

/**
 * Created by dgaglani on 9/2/14.
 */
public class ExecutorServiceHealthCheck extends HealthCheck{

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
