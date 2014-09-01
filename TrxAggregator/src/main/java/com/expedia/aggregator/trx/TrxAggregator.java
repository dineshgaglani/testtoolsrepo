package com.expedia.aggregator.trx;

import com.expedia.aggregator.data.*;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dgaglani on 8/26/14.
 */
public class TrxAggregator extends ParallelAggregator<TestRunType> {

    public void aggregateTrxFiles(List<File> trxFiles, String mergedFileLocation) throws Exception{
        List<TestRunType> testRuns = new ArrayList<TestRunType>();
        for(File trxFile : trxFiles) {
            testRuns.add(getTestRunFromFile(trxFile));
        }
        TestRunType mergedTestRun = mergeElements(testRuns);
        convertMergedTrxToFile(mergedTestRun, mergedFileLocation);
    }

    public void convertMergedTrxToFile(TestRunType mergedTestRun, String mergedFileLocation) throws Exception {
        Marshaller marshaller = JAXBContext.newInstance(TestRunType.class).createMarshaller();
        marshaller.marshal(mergedTestRun, new File(mergedFileLocation));
    }

    public TestRunType getTestRunFromFile(File trxFile) throws Exception{
        JAXBContext jaxbContext = JAXBContext.newInstance(TestRunType.class);
        return (TestRunType)jaxbContext.createUnmarshaller().unmarshal(trxFile);
    }

    @Override
    public TestRunType performMerge(TestRunType elementToMerge1, TestRunType elementToMerge2) {
        TestRunType.ResultSummary resultSummary1 = getResultsSummary(elementToMerge1);
        TestRunType.ResultSummary resultSummary2 = getResultsSummary(elementToMerge2);
        CountersType result1Counters = (CountersType)resultSummary1.getCountersOrOutputOrRunInfos().get(0);
        CountersType result2Counters = (CountersType)resultSummary2.getCountersOrOutputOrRunInfos().get(0);
        mergeCounters(result1Counters, result2Counters);
        mergeTestDefinitions(elementToMerge1, elementToMerge2);
        return elementToMerge1;
    }

    public void mergeTestDefinitions(TestRunType elementToMerge1, TestRunType elementToMerge2) {
        int testDefinitionUnitTestPosition = 0;
        TestRunType.TestDefinitions definitions1 = getTestDefinitions(elementToMerge1);
        TestRunType.TestDefinitions definitions2 = getTestDefinitions(elementToMerge2);
        if(definitions1.getUnitTestOrUnitTestElementOrManualTest().size() < definitions2.getUnitTestOrUnitTestElementOrManualTest().size()) {
            //Merging the smaller with the bigger
            TestRunType.TestDefinitions temp = definitions1;
            definitions1 = definitions2;
            definitions2 = temp;
        }
        for(Object unitTestFromSecondTrx : definitions2.getUnitTestOrUnitTestElementOrManualTest()) {
            definitions1.getUnitTestOrUnitTestElementOrManualTest().add(unitTestFromSecondTrx);
            UnitTestType unitTest = ((JAXBElement<UnitTestType>)unitTestFromSecondTrx).getValue();
            //Merge the corresponding test entry
            String testEntryExecutionId = unitTest.getId();
            TestEntriesType testEntriesFromSecondTrx = getTestEntries(elementToMerge2);
            TestEntryType testEntryFromSecondTrx = getTestEntryByTestId(testEntryExecutionId, testDefinitionUnitTestPosition, testEntriesFromSecondTrx);
            TestEntriesType testEntriesFromFirstTrx = getTestEntries(elementToMerge1);
            testEntriesFromFirstTrx.getTestEntry().add(testEntryFromSecondTrx);
            //Merge the corresponding unit test result
            ResultsType testResultsFromSecondTrx = getResultsType(elementToMerge2);
            JAXBElement<UnitTestResultType> secondTrxUnitTestResult = getUnitTestResultByTestId(testEntryFromSecondTrx.getTestId(), testDefinitionUnitTestPosition, testResultsFromSecondTrx);
            ResultsType testResultsFromFirstTrx = getResultsType(elementToMerge1);
            testResultsFromFirstTrx.getUnitTestResultOrGenericTestResultOrTestResult().add(secondTrxUnitTestResult);
            testDefinitionUnitTestPosition++;
        }
    }

    public ResultsType getResultsType(TestRunType testRun) {
       return ((ResultsType)testRun.getTestRunConfigurationOrTestSettingsOrResultSummary().get(6));
    }

    public TestEntriesType getTestEntries(TestRunType testRun) {
        return (TestEntriesType)testRun.getTestRunConfigurationOrTestSettingsOrResultSummary().get(5);
    }

    public TestEntryType getTestEntryByTestId(String executionId, int positionOfUnitTest, TestEntriesType testEntries) {
        //This should ideally be found at position of unit test, but just in case it is not found, searching linearly..
        if (testEntries.getTestEntry().get(positionOfUnitTest).getTestId().equals(executionId)) {
            return testEntries.getTestEntry().get(positionOfUnitTest);
        } else {
            for(TestEntryType testEntry : testEntries.getTestEntry()) {
                if(testEntry.getExecutionId().equals(executionId)) {
                    return testEntry;
                }
            }
        }
        return null;
    }

    public JAXBElement<UnitTestResultType> getUnitTestResultByTestId(String testId, int positionOfUnitTest, ResultsType testResult) {
        JAXBElement<UnitTestResultType> unitTestResultTypeJAXBElement = (JAXBElement<UnitTestResultType>)testResult.getUnitTestResultOrGenericTestResultOrTestResult().get(positionOfUnitTest);
        if(unitTestResultTypeJAXBElement.getValue().getTestId().equals(testId))  {
            return unitTestResultTypeJAXBElement;
        }
        for (Object unitTestResultFromSecondTrx : testResult.getUnitTestResultOrGenericTestResultOrTestResult()) {
            UnitTestResultType unitTestResult = ((JAXBElement<UnitTestResultType>)unitTestResultFromSecondTrx).getValue();
            if(unitTestResult.getTestId().equals(testId)) {
                return (JAXBElement<UnitTestResultType>)unitTestResultFromSecondTrx;
            }
        }
        return null;
    }

    public TestRunType.TestDefinitions getTestDefinitions(TestRunType testRun) {
        return (TestRunType.TestDefinitions)testRun.getTestRunConfigurationOrTestSettingsOrResultSummary().get(3);
    }

    public TestRunType.ResultSummary getResultsSummary(TestRunType testRun) {
        return (TestRunType.ResultSummary)testRun.getTestRunConfigurationOrTestSettingsOrResultSummary().get(2);
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

    public void aggregateTrxFromFileList(List<String> filesList, String mergedFileLocation) throws Exception {
        List<File> trxFilesFromPaths = getTrxFilesFromPaths(filesList);
        aggregateTrxFiles(trxFilesFromPaths, mergedFileLocation);
    }

    public static List<File> getTrxFilesFromPaths(List<String> filePaths) {
        ArrayList<File> files = new ArrayList<File>();
        for (String filePath : filePaths) {
            files.add(new File(filePath));
        }
        return files;
    }

}
