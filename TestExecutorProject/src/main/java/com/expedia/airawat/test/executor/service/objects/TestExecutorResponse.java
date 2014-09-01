package com.expedia.airawat.test.executor.service.objects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by dgaglani on 9/1/14.
 */
@XmlRootElement
public class TestExecutorResponse {

    private String testRunId;
    private String mergedFileAccessURL;
    private String completePercent;

    @XmlElement
    public String getTestRunId() {
        return testRunId;
    }

    public void setTestRunId(String testRunId) {
        this.testRunId = testRunId;
    }

    @XmlElement
    public String getMergedFileAccessURL() {
        return mergedFileAccessURL;
    }

    public void setMergedFileAccessURL(String mergedFileAccessURL) {
        this.mergedFileAccessURL = mergedFileAccessURL;
    }

    @XmlElement
    public String getCompletePercent() {
        return completePercent;
    }

    public void setCompletePercent(String completePercent) {
        this.completePercent = completePercent;
    }
}
