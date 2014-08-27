package com.expedia.aggregator.trx;

import com.expedia.aggregator.data.CountersType;
import com.expedia.aggregator.data.TestRunType;

import javax.xml.bind.JAXBContext;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dgaglani on 8/26/14.
 */
public class TrxAggregator extends ParallelAggregator<TestRunType> {

    public void aggregateTrxFiles(List<File> trxFiles) throws Exception{
        List<TestRunType> testRuns = new ArrayList<TestRunType>();
        for(File trxFile : trxFiles) {
            testRuns.add(getTestRunFromFile(trxFile));
        }
        TestRunType mergedTestRun = mergeElements(testRuns);
    }

    public TestRunType getTestRunFromFile(File trxFile) throws Exception{
        JAXBContext jaxbContext = JAXBContext.newInstance(TestRunType.class);
        return (TestRunType)jaxbContext.createUnmarshaller().unmarshal(trxFile);
    }

    @Override
    public TestRunType performMerge(TestRunType elementToMerge1, TestRunType elementToMerge2) {
        TestRunType.ResultSummary resultSummary1 = getResultsSummary(elementToMerge1);
        TestRunType.ResultSummary resultSummary2 = getResultsSummary(elementToMerge2);
        CountersType result1Counters = (CountersType)resultSummary1.getCountersOrOutputOrRunInfos();
        CountersType result2Counters = (CountersType)resultSummary2.getCountersOrOutputOrRunInfos();
        mergeCounters(result1Counters, result2Counters);
        return null;
    }

    public TestRunType.ResultSummary getResultsSummary(TestRunType testRun) {
        return (TestRunType.ResultSummary)testRun.getTestRunConfigurationOrTestSettingsOrResultSummary().get(0);
    }

    public void mergeCounters(CountersType result1Counters, CountersType result2Counters) {
        //<Counters total="20" executed="20" error="0" failed="20" timeout="0" aborted="0" inconclusive="0" passedButRunAborted="0" notRunnable="0" notExecuted="0" disconnected="0" warning="0" passed="0" completed="0" inProgress="0" pending="0" />
        result1Counters.setTotal(result1Counters.getTotal() + result2Counters.getTotal());
        result1Counters.setExecuted(result1Counters.getExecuted() + result2Counters.getExecuted());
        result1Counters.setError(result1Counters.getError() + result2Counters.getError());
        result1Counters.setFailed(result1Counters.getFailed() + result2Counters.getFailed());
        result1Counters.setTimeout(result1Counters.getTimeout() + result2Counters.getTimeout());
        result1Counters.setInconclusive(result1Counters.getInconclusive() + result2Counters.getInconclusive());
        result1Counters.setPassedButRunAborted(result1Counters.getPassedButRunAborted() + result2Counters.getPassedButRunAborted());
        result1Counters.setNotRunnable(result1Counters.getNotRunnable() + result2Counters.getNotRunnable());
        result1Counters.setNotExecuted(result1Counters.getNotExecuted() + result2Counters.getNotExecuted());
        result1Counters.setDisconnected(result1Counters.getDisconnected() + result2Counters.getDisconnected());
        result1Counters.setWarning(result1Counters.getWarning() + result2Counters.getWarning());
        result1Counters.setPassed(result1Counters.getPassed() + result2Counters.getPassed());
        result1Counters.setCompleted(result1Counters.getCompleted() + result2Counters.getCompleted());
        result1Counters.setInProgress(result1Counters.getInProgress() + result2Counters.getInProgress());
        result1Counters.setPending(result1Counters.getPending() + result2Counters.getPending());
    }
}
