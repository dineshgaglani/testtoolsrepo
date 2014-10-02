package com.expedia.airawat.test.executor.service;


import com.expedia.airawat.test.executor.service.configs.Paths;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by dgaglani on 8/31/14.
 */
public class TestExecutorDropwizardServiceConfiguration extends Configuration {


    @NotNull
    @Valid
    @JsonProperty
    private Paths paths;

    public Paths getPaths() {
        return paths;
    }

}
