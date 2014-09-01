import com.expedia.aggregator.data.*;
import org.testng.Assert;

import javax.xml.bind.JAXBElement;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dgaglani on 8/28/14.
 */
public class TrxValidators {

    private TestRunType testRunTypeToValidate;
    private TestRunType testRunTypeToMerge;
    private TestRunType testRunTypePreMerged;

    TrxValidators(TestRunType testRunType, TestRunType testRunTypeToMerge, TestRunType testRunTypePreMerged) {
        this.testRunTypeToValidate = testRunType;
        this.testRunTypeToMerge = testRunTypeToMerge;
        this.testRunTypePreMerged = testRunTypePreMerged;
    }

    public void validateCounters() {
        //<Counters total="20" executed="20" error="0" failed="20" timeout="0" aborted="0" inconclusive="0" passedButRunAborted="0" notRunnable="0" notExecuted="0" disconnected="0" warning="0" passed="0" completed="0" inProgress="0" pending="0" />
        CountersType testRunTypeToValidateCounters = getCounters(testRunTypeToValidate);
        CountersType testRunTypeToMergeCounters = getCounters(testRunTypeToMerge);
        CountersType testRunTypePreMergedCounters = getCounters(testRunTypePreMerged);
        Assert.assertTrue(testRunTypeToValidateCounters.getTotal() == testRunTypeToMergeCounters.getTotal() + testRunTypePreMergedCounters.getTotal());
        Assert.assertTrue(testRunTypeToValidateCounters.getExecuted() == testRunTypeToMergeCounters.getExecuted() + testRunTypePreMergedCounters.getExecuted());
        Assert.assertTrue(testRunTypeToValidateCounters.getError() == testRunTypeToMergeCounters.getError() + testRunTypePreMergedCounters.getError());
        Assert.assertTrue(testRunTypeToValidateCounters.getFailed() == testRunTypeToMergeCounters.getFailed() + testRunTypePreMergedCounters.getFailed());
        Assert.assertTrue(testRunTypeToValidateCounters.getTimeout() == testRunTypeToMergeCounters.getTimeout() + testRunTypePreMergedCounters.getTimeout());
        Assert.assertTrue(testRunTypeToValidateCounters.getAborted() == testRunTypeToMergeCounters.getAborted() + testRunTypePreMergedCounters.getAborted());
        Assert.assertTrue(testRunTypeToValidateCounters.getInconclusive() == testRunTypeToMergeCounters.getInconclusive() + testRunTypePreMergedCounters.getInconclusive());
        Assert.assertTrue(testRunTypeToValidateCounters.getPassedButRunAborted() == testRunTypeToMergeCounters.getPassedButRunAborted() + testRunTypePreMergedCounters.getPassedButRunAborted());
        Assert.assertTrue(testRunTypeToValidateCounters.getNotRunnable() == testRunTypeToMergeCounters.getNotRunnable() + testRunTypePreMergedCounters.getNotRunnable());
        Assert.assertTrue(testRunTypeToValidateCounters.getNotExecuted() == testRunTypeToMergeCounters.getNotExecuted() + testRunTypePreMergedCounters.getNotExecuted());
        Assert.assertTrue(testRunTypeToValidateCounters.getDisconnected() == testRunTypeToMergeCounters.getDisconnected() + testRunTypePreMergedCounters.getDisconnected());
        Assert.assertTrue(testRunTypeToValidateCounters.getWarning() == testRunTypeToMergeCounters.getWarning() + testRunTypePreMergedCounters.getWarning());
        Assert.assertTrue(testRunTypeToValidateCounters.getPassed() == testRunTypeToMergeCounters.getPassed() + testRunTypePreMergedCounters.getPassed());
        Assert.assertTrue(testRunTypeToValidateCounters.getCompleted() == testRunTypeToMergeCounters.getCompleted() + testRunTypePreMergedCounters.getCompleted());
        Assert.assertTrue(testRunTypeToValidateCounters.getInProgress() == testRunTypeToMergeCounters.getInProgress() + testRunTypePreMergedCounters.getInProgress());
        Assert.assertTrue(testRunTypeToValidateCounters.getPending() == testRunTypeToMergeCounters.getPending() + testRunTypePreMergedCounters.getPending());
    }

    public void validateTestDefinition() {
        ArrayList<TestRunType.TestDefinitions> combinedTestDefintionList = new ArrayList<TestRunType.TestDefinitions>();
        combinedTestDefintionList.addAll((List)getTestDefintions(testRunTypeToMerge).getUnitTestOrUnitTestElementOrManualTest());
        combinedTestDefintionList.addAll((List)getTestDefintions(testRunTypePreMerged).getUnitTestOrUnitTestElementOrManualTest());
        List testRunTypeDefinitionsToValidate = getTestDefintions(testRunTypeToValidate).getUnitTestOrUnitTestElementOrManualTest();
        //Try finding the merged list element in the just combined list
        for(Object mergedListUnitTestJaxbObj : testRunTypeDefinitionsToValidate) {
            UnitTestType mergedListUnitTest = ((JAXBElement<UnitTestType>)mergedListUnitTestJaxbObj).getValue();
            int combinedTestDefintionListUnitTestCounter = 0;
            for (Object unitTestJaxbObj : combinedTestDefintionList) {
                UnitTestType combinedTestDefintionListUnitTest = ((JAXBElement<UnitTestType>)unitTestJaxbObj).getValue();
                if(mergedListUnitTest.getName().equals(combinedTestDefintionListUnitTest.getName())) {
                    break;
                }
                combinedTestDefintionListUnitTestCounter++;
                if(combinedTestDefintionListUnitTestCounter == combinedTestDefintionList.size()) {
                    Assert.fail("The test " + combinedTestDefintionListUnitTest.getName() + " was not found in the merged list ");
                }
            }
        }
    }

    public void validateTestEntries() {
        ArrayList<TestEntryType> combinedTestEntriesList = new ArrayList<TestEntryType>();
        combinedTestEntriesList.addAll((List)getTestEntries(testRunTypeToMerge));
        combinedTestEntriesList.addAll((List)getTestEntries(testRunTypePreMerged));
        for(TestEntryType mergedListTestEntry : getTestEntries(testRunTypeToValidate)) {
            int combinedTestEntriesCount = 0;
            for(TestEntryType testEntryToFind : combinedTestEntriesList) {
                if(mergedListTestEntry.getTestId().equals(testEntryToFind.getTestId())) {
                    break;
                }
                combinedTestEntriesCount++;
                if(combinedTestEntriesCount == combinedTestEntriesList.size()) {
                    Assert.fail("The test with test ID : " + testEntryToFind.getTestId() +" not found in the merged list test entries ");
                }
            }
        }
    }

    public void validateUnitTestResults() {
        ArrayList<TestEntryType> combinedUnitTestResultsList = new ArrayList<TestEntryType>();
        combinedUnitTestResultsList.addAll((List) getUnitTestResults(testRunTypeToMerge));
        combinedUnitTestResultsList.addAll((List) getUnitTestResults(testRunTypePreMerged));
        for(Object mergedUnitTestResultsList : getUnitTestResults(testRunTypeToValidate)) {
            UnitTestResultType mergedUnitTestResult = ((JAXBElement<UnitTestResultType>)mergedUnitTestResultsList).getValue();
            int combinedUnitTestResultCount = 0;
            for(Object unitTestResultToFindJaxbObject : combinedUnitTestResultsList) {
                UnitTestResultType unitTestResultToFind = ((JAXBElement<UnitTestResultType>)unitTestResultToFindJaxbObject).getValue();
                if(mergedUnitTestResult.getTestId().equals(unitTestResultToFind.getTestId())) {
                    break;
                }
                combinedUnitTestResultCount++;
                if(combinedUnitTestResultCount == combinedUnitTestResultsList.size()) {
                    Assert.fail("The test with test ID : " + unitTestResultToFind.getTestId() +" not found in the merged list unit test results ");
                }
            }
        }
    }

    public static List<TestEntryType> getTestEntries(TestRunType testRun) {
        return ((TestEntriesType)testRun.getTestRunConfigurationOrTestSettingsOrResultSummary().get(5)).getTestEntry();
    }

    public TestRunType.TestDefinitions getTestDefintions(TestRunType testRunType) {
        return (TestRunType.TestDefinitions)testRunType.getTestRunConfigurationOrTestSettingsOrResultSummary().get(3);
    }

    private static CountersType getCounters(TestRunType testRunType) {
        return (CountersType)getResultsSummary(testRunType).getCountersOrOutputOrRunInfos().get(0);
    }

    public static TestRunType.ResultSummary getResultsSummary(TestRunType testRun) {
        return (TestRunType.ResultSummary)testRun.getTestRunConfigurationOrTestSettingsOrResultSummary().get(2);
    }

    public static ResultsType getResultsType(TestRunType testRun) {
        return ((ResultsType)testRun.getTestRunConfigurationOrTestSettingsOrResultSummary().get(6));
    }

    public static List<Object> getUnitTestResults(TestRunType testRun) {
       return getResultsType(testRun).getUnitTestResultOrGenericTestResultOrTestResult();
    }

}
