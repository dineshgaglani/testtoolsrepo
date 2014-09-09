package com.expedia.airawat.test.runner;

import com.expedia.airawat.test.executor.engine.TestExecutor;

import java.io.File;
import java.util.concurrent.Callable;

/**
 * Created by dgaglani on 9/1/14.
 */
public class ParallelTestExecutor implements Callable<String> {

    private String testName;
    private String testResultsFileNamePart;
    private TestExecutor.CompletedThreadsTracker completedThreadsTracker;
    private TestRunner testRunner;
    private String executorFilePath;

    public ParallelTestExecutor(TestRunner testRunner, String executorFilePath, String testNames, String testResultsFileNamePart, TestExecutor.CompletedThreadsTracker completedThreadsTracker) {
        this.testName = testNames;
        this.testResultsFileNamePart = testResultsFileNamePart;
        this.completedThreadsTracker = completedThreadsTracker;
        this.testRunner = testRunner;
        this.executorFilePath = executorFilePath;
    }

    @Override
    public String call() throws Exception {
        String testResultFileName = testResultsFileNamePart + "-" + testName;
        testRunner.executeTest(executorFilePath, testName, testResultFileName);
        String resultsDirectory = executorFilePath.substring(0, executorFilePath.lastIndexOf(System.getProperty("file.separator")) + 1);
        completedThreadsTracker.increment();
        if (!new File(resultsDirectory + testResultFileName).exists()) {
            //TODO - Log the test execution failure here
            System.out.print("Results file not created for testName " + testName);
            return null;
        } else {
            return new File(resultsDirectory + testResultFileName).getAbsolutePath();
        }
    }
}


