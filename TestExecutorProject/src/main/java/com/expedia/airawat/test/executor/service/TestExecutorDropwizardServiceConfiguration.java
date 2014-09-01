package com.expedia.airawat.test.executor.service;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by dgaglani on 8/31/14.
 */
public class TestExecutorDropwizardServiceConfiguration extends Configuration {

    @NotEmpty
    private int portNumber;

    @JsonProperty
    public int getPortNumber() {
        return portNumber;
    }

    @JsonProperty
    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }
}
