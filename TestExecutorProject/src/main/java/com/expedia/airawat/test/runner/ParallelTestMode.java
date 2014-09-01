package com.expedia.airawat.test.runner;

import com.expedia.airawat.test.executor.engine.TestExecutor;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by dgaglani on 8/21/14.
 */
public class ParallelTestMode implements TestMode<List<String>> {

    TestRunner testRunner;
    String executorFilePath;
    ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(100));

    @Override
    public List<String> runTest(TestRunner testRunner, List<String> testNames, String executorFilePath, String testResultFileNamePart, TestExecutor.CompletedThreadsTracker completedThreadsTracker) {
        this.testRunner = testRunner;
        this.executorFilePath = executorFilePath;
        List<ListenableFuture<List<String>>> futureSerialTestResultsFileNames = new ArrayList<ListenableFuture<List<String>>>();
        List<String> resultsFiles = new ArrayList<String>();
        for(String testName : testNames) {
            String[] serialTestsList = testName.split(",");
            futureSerialTestResultsFileNames.add(executorService.submit(new SerialTestRunner(testRunner, executorFilePath, Arrays.asList(serialTestsList), testResultFileNamePart, completedThreadsTracker)));
        }
        //Thread t = new Thread(this.new CompletedChecker(this), "completionChecker");
        //t.start();
        try {
            List<List<String>> serialTestResults = Futures.allAsList(futureSerialTestResultsFileNames).get();
            for(List<String> serialTestResult : serialTestResults) {
                resultsFiles.addAll(serialTestResult);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return resultsFiles;
    }

    //For testing completion of threads
    /*public class CompletedChecker implements Runnable {

        ParallelTestMode testMode;

        public CompletedChecker(ParallelTestMode testMode) {
            this.testMode = testMode;
        }

        @Override
        public void run() {
            while (testMode.getThreadsTracker().getCompletedThreadsCount() != 3) {
                System.out.print("completed threads " + testMode.getThreadsTracker().getCompletedThreadsCount());
                try{
                    Thread.sleep(1*100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }*/

}
