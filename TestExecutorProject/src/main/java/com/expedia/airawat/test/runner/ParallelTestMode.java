package com.expedia.airawat.test.runner;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * Created by dgaglani on 8/21/14.
 */
public class ParallelTestMode implements TestMode<List<String>> {

    TestRunner testRunner;
    String executorFilePath;
    ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(100));

    private class ParallelTestModeRunner implements Callable<String> {

        String testName;
        String testResultsFileName;

        ParallelTestModeRunner(String testName, String testResultsFileName) {
            this.testName = testName;
            this.testResultsFileName = testResultsFileName;
        }

        @Override
        public String call() {
            //Will return testResults file
            testRunner.executeTest(executorFilePath, testName, testResultsFileName);
            String resultsDirectory = executorFilePath.substring(0, executorFilePath.lastIndexOf(System.getProperty("file.separator")) + 1);
            if(new File(resultsDirectory + testResultsFileName).exists()) {
                return resultsDirectory + testResultsFileName;
            }
            System.out.print("Results file not created for testName " + testName);
            return null;
        }
    }

    @Override
    public List<String> runTest(TestRunner testRunner, String[] testNames, String executorFilePath, String testResultFileNamePart) {
        this.testRunner = testRunner;
        this.executorFilePath = executorFilePath;
        List<ListenableFuture<String>> futureTestResultsFileNames = new ArrayList<ListenableFuture<String>>();
        List<String> resultsFiles = null;
        for(String testName : testNames) {
            String testResultFileName = testResultFileNamePart + "-" + testName;
            futureTestResultsFileNames.add(executorService.submit(new ParallelTestModeRunner(testName, testResultFileName)));
        }
        try {
            resultsFiles = Futures.allAsList(futureTestResultsFileNames).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return resultsFiles;
    }


}
