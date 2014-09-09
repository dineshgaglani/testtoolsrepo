package com.expedia.airawat.test.executor.service.objects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by dgaglani on 9/8/14.
 */
@XmlRootElement
public class TVARegressionResults {

    private List<String> executionCompletePercent;
    private String resultFileLocation;
    private String preRunCompletionPercent;
    private String postRunCompletionPercent;


    @XmlElement
    public List<String> getExecutionCompletePercent() {
        return executionCompletePercent;
    }

    public void setExecutionCompletePercent(List<String> executionCompletePercent) {
        this.executionCompletePercent = executionCompletePercent;
    }

    @XmlElement
    public String getResultFileLocation() {
        return resultFileLocation;
    }

    public void setResultFileLocation(String resultFileLocation) {
        this.resultFileLocation = resultFileLocation;
    }

    @XmlElement
    public String getPreRunCompletionPercent() {
        return preRunCompletionPercent;
    }

    public void setPreRunCompletionPercent(String preRunCompletionPercent) {
        this.preRunCompletionPercent = preRunCompletionPercent;
    }

    @XmlElement
    public String getPostRunCompletionPercent() {
        return postRunCompletionPercent;
    }

    public void setPostRunCompletionPercent(String postRunCompletionPercent) {
        this.postRunCompletionPercent = postRunCompletionPercent;
    }
}
