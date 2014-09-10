package com.expedia.airawat.test.executor.service.objects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by dgaglani on 8/31/14.
 */

@XmlRootElement
public class TestExecutionRequest {

    private String testRunnerType;
    private List<List<String>> testsList;
    private String resultFileType;
    private String executorLocation;
    private String resultFileLocation;

    @XmlElement
    public String getTestRunnerType() {
        return testRunnerType;
    }

    public void setTestRunnerType(String testRunnerType) {
        this.testRunnerType = testRunnerType;
    }

    @XmlElement
    public List<List<String>> getTestsList() {
        return testsList;
    }

    public void setTestsList(List<List<String>> testsList) {
        this.testsList = testsList;
    }

    @XmlElement
    public String getResultFileType() {
        return resultFileType;
    }

    public void setResultFileType(String resultFileType) {
        this.resultFileType = resultFileType;
    }

    @XmlElement
    public String getExecutorLocation() {
        return executorLocation;
    }

    public void setExecutorLocation(String executorLocation) {
        this.executorLocation = executorLocation;
    }

    @XmlElement
    public String getResultFileLocation() {
        return resultFileLocation;
    }

    public void setResultFileLocation(String resultFileLocation) {
        this.resultFileLocation = resultFileLocation;
    }
}
