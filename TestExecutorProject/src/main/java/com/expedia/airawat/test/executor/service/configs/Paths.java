package com.expedia.airawat.test.executor.service.configs;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class Paths {

	@JsonProperty
	@NotNull
    public String pathsWhereTrxGo;

    @JsonProperty
    public boolean runPreProdForRegression;

    @JsonProperty
    public boolean runCharterForRegression;

    public String getPathsWhereTrxGo() {
        return pathsWhereTrxGo;
    }

    public boolean isRunPreProdForRegression() {
        return runPreProdForRegression;
    }

    public boolean isRunCharterForRegression() { return runCharterForRegression; }
}
