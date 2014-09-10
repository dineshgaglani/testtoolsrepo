package com.expedia.airawat.test.executor.service;

import com.expedia.airawat.test.executor.engine.TestExecutor;
import com.expedia.airawat.test.executor.service.objects.TestExecutorResponse;
import com.expedia.airawat.test.runner.ParallelTestMode;
import com.google.common.io.Files;

import java.io.File;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

/**
 * Created by dgaglani on 9/1/14.
 */
public class ServiceUtils {

    private static ConcurrentHashMap<String, TestExecutor> executionIdToTestExecutorMap = new ConcurrentHashMap<String, TestExecutor>();
    private static ConcurrentHashMap<String, String> executionIdToMergedTestResultMap = new ConcurrentHashMap<String, String>();
    private static ConcurrentHashMap<String, Integer> executionIdNumberOfTestsMap = new ConcurrentHashMap<String, Integer>();

    public static void addToExecutionIdToTestExecutorMap(String token, TestExecutor testExecutor) {
        executionIdToTestExecutorMap.put(token, testExecutor);
    }

    public static void addToTokenToNumberOfTestsMap(String token, Integer numberOfTestsForToken) {
        executionIdNumberOfTestsMap.put(token, numberOfTestsForToken);
    }

    public static void addToTestsCompletedMap(String token, String testExecutionResultFile) {
        executionIdToMergedTestResultMap.put(token, testExecutionResultFile);
    }

    public static TestExecutorResponse getExecutedPercentageOrResultsFile(String token) throws Exception {
        int testCompleted = 0;
        TestExecutorResponse response = new TestExecutorResponse();
        if(executionIdToTestExecutorMap.keySet().contains(token)) {
            TestExecutor executorInstance = executionIdToTestExecutorMap.get(token);
            testCompleted = (executorInstance.getThreadsTracker().getCompletedThreadsCount() * 100)/executionIdNumberOfTestsMap.get(token);
            response.setCompletePercent(testCompleted+"");
            if(testCompleted == 100) {
                addToTestsCompletedMap(token, getMergedFileResultsPath(token));
                response.setMergedFileAccessURL(getMergedFileResultsPath(token));
                executionIdToTestExecutorMap.remove(token);
            }
        } else if(executionIdToMergedTestResultMap.containsKey(token)) {
            response.setCompletePercent(100+"");
            response.setMergedFileAccessURL(getMergedFileResultsPath(token));
        }
        return response;
    }

    public static Integer getNumberOfTests(List<List<String>> testNames) {
        int testCount = 0;
        for(List<String> testSet : testNames) {
            testCount = testCount + testSet.size();
        }
        return testCount;
    }

    public static String getExecutorFilePath() {
        //TODO - comes from wherever the user has checked in his artifacts
        return System.getProperty("user.home") + System.getProperty("file.separator") + "testBatchFile.sh";
    }

    public static String getMergedFileResultsPath(String token) {
        //TODO - depends on the token generated
        return System.getProperty("user.home") + System.getProperty("file.separator") + token;
    }

    public static String getTVARegressionExecutorFilePath() {
        return System.getProperty("user.home") + System.getProperty("file.separator");
    }

    public static String generateRandomKey() {
        SecureRandom random = new SecureRandom();
        String randomKey = new BigInteger(130, random).toString(32);
        if (executionIdToTestExecutorMap.keySet().contains(randomKey) || executionIdToMergedTestResultMap.contains(randomKey)) {
            randomKey = generateRandomKey();
        }
        return randomKey;
    }


}
