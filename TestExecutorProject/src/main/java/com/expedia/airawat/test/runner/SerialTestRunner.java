package com.expedia.airawat.test.runner;

import com.expedia.airawat.test.executor.engine.TestExecutor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by dgaglani on 9/1/14.
 */
public class SerialTestRunner implements Callable<List<String>> {

        private List<String> testNames;
        private String testResultsFileNamePart;
        private TestExecutor.CompletedThreadsTracker completedThreadsTracker;
        private TestRunner testRunner;
        private String executorFilePath;

        public SerialTestRunner(TestRunner testRunner, String executorFilePath, List<String> testNames, String testResultsFileNamePart, TestExecutor.CompletedThreadsTracker completedThreadsTracker) {
            this.testNames = testNames;
            this.testResultsFileNamePart = testResultsFileNamePart;
            this.completedThreadsTracker = completedThreadsTracker;
            this.testRunner = testRunner;
            this.executorFilePath = executorFilePath;
        }

        @Override
        public List<String> call() throws Exception {
            List<String> testResultFileNames = new ArrayList<String>();
            for(String testName : testNames) {
                String testResultFileName = testResultsFileNamePart + "-" + testName;
                testRunner.executeTest(executorFilePath, testName, testResultFileName);
                String resultsDirectory = executorFilePath.substring(0, executorFilePath.lastIndexOf(System.getProperty("file.separator")) + 1);
                if(new File(resultsDirectory + testResultFileName).exists()) {
                    completedThreadsTracker.increment();
                    testResultFileNames.add(resultsDirectory + testResultFileName);
                } else {
                    System.out.print("Results file not created for testName " + testName);
                    testResultFileNames.add(null);
                }
            }
            return testResultFileNames;
        }

}
