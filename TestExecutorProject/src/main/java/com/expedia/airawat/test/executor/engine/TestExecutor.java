package com.expedia.airawat.test.executor.engine;

import com.expedia.aggregator.trx.TrxAggregator;
import com.expedia.airawat.test.runner.ParallelTestMode;
import com.expedia.airawat.test.runner.TestMode;
import com.expedia.airawat.test.runner.TestRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by dgaglani on 8/20/14.
 */
@ContextConfiguration( locations={"classpath:application-context.xml"} )
public class TestExecutor {

    @Autowired
    private Environment env;
    private CompletedThreadsTracker completedThreadsTracker;

    public static void main(String args[]) throws Exception {
        TestExecutor testExecutor = new TestExecutor();
        String[] argsString = args[0].split(" ");
        String testType = argsString[0];
        String[] testNames = argsString[1].split(",");
        String executorFilePath = argsString[2];
        String resultsFileExtension = argsString[3];
        String resultsFilePath = argsString[4];
        testExecutor.executeTests(testType, Arrays.asList(testNames), executorFilePath, resultsFileExtension, resultsFilePath);
    }

    public class RunnableExecutor implements Runnable {

        private String testType;
        private List<String> testNames;
        private String executorFilePath;
        private String resultsFileExtension;
        private String resultsFilePath;

        public RunnableExecutor(String testType, List<String> testNames, String executorFilePath, String resultsFileExtension, String resultsFilePath) {
            this.testType = testType;
            this.testNames = testNames;
            this.executorFilePath = executorFilePath;
            this.resultsFileExtension = resultsFileExtension;
            this.resultsFilePath = resultsFilePath;
        }

        @Override
        public void run() {
            try {
                executeTests(testType, testNames, executorFilePath, resultsFileExtension, resultsFilePath);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void executeTests(String testType, List<String> testNames, String executorFilePath, String resultsFileExtension, String resultsFilePath) throws Exception {
       TestRunner testRunner = getTestRuntime(testType);
        String testExecutionMode = this.env.getProperty("executionMode");
        TestMode testMode = null;
        if(testExecutionMode.equals("Parallel")) {
            testMode = new ParallelTestMode();
        } else if (testExecutionMode.equals("Serial")) {
            testMode = null;
        }
        String testTimeStamp = new Long(System.currentTimeMillis()).toString();
        completedThreadsTracker = new TestExecutor().new CompletedThreadsTracker();
        List<String> testResultFiles = (List<String>)testMode.runTest(testRunner, testNames, executorFilePath, testTimeStamp, completedThreadsTracker);
        aggregateFiles(testResultFiles, resultsFileExtension, resultsFilePath);
    }

    private TestRunner getTestRuntime(String testType) {
        //Get the class for the corresponding test-type from the property file
        String runnerImplForTestType = env.getProperty(testType);
        try{
            return (TestRunner)Class.forName(runnerImplForTestType).newInstance();
        }
        catch(ClassNotFoundException e) {

        }
        catch(IllegalAccessException e) {

        }
        catch (InstantiationException e) {

        }
        return null;
    }

    public void aggregateFiles(List<String> testResultFiles, String testResultFileExtension, String mergedFileLocation) throws Exception {
        if(testResultFileExtension.equals(".trx")) {
            TrxAggregator trxAggregator = new TrxAggregator();
            trxAggregator.aggregateTrxFromFileList(testResultFiles, mergedFileLocation);
        }
    }

    public CompletedThreadsTracker getThreadsTracker() {
        return completedThreadsTracker;
    }

    public class CompletedThreadsTracker {

        AtomicInteger completedThreadsCounter = new AtomicInteger(0);

        public void increment() {
            completedThreadsCounter.incrementAndGet();
        }

        public void decrement() {
            completedThreadsCounter.decrementAndGet();
        }

        public int getCompletedThreadsCount() {
            return completedThreadsCounter.get();
        }
    }

}
