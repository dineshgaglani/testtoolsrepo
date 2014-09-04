package com.expedia.airawat.test.executor.service;

import com.expedia.airawat.test.executor.engine.TestExecutor;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dgaglani on 9/1/14.
 */
public class ServiceUtils {

    private static ConcurrentHashMap<String, TestExecutor> executionIdToTestsInProgressMap = new ConcurrentHashMap<String, TestExecutor>();
    private static ConcurrentHashMap<String, String> executionIdToMergedTestResultMap = new ConcurrentHashMap<String, String>();

    public static void addToTestsInProgressMap(String token, TestExecutor testExecutor) {
        executionIdToTestsInProgressMap.put(token, testExecutor);
    }

    public static void addToTestsCompletedMap(String token, String testExecutionResultFile) {
        executionIdToMergedTestResultMap.put(token, testExecutionResultFile);
    }

    public static String getExecutedPercentageOrResultsFile(String token) throws Exception {
        int testCompleted = 0;
        if(executionIdToTestsInProgressMap.keySet().contains(token)) {
            TestExecutor executorInstance = executionIdToTestsInProgressMap.get(token);
            testCompleted = (executorInstance.getThreadsTracker().getCompletedThreadsCount() * 100)/(executionIdToTestsInProgressMap.size() + executionIdToMergedTestResultMap.size());
            if(testCompleted == 100) {
                addToTestsCompletedMap(token, getMergedFileResultsPath());
                executionIdToTestsInProgressMap.remove(token);
            }
        }
        return testCompleted+"";
    }

    public static String getExecutorFilePath() {
        //TODO - comes from wherever the user has checked in his artifacts
        return "/Users/dgaglani/Desktop/tasks/Airawat/TestExecutorProject/src/main/resources/testBatchFile.sh";
    }

    public static String getMergedFileResultsPath() {
        //TODO - depends on the token generated
        return "/Users/dgaglani/Desktop/tasks/Airawat/TestExecutorProject/src/main/resources";
    }

    public static String generateRandomKey() {
        SecureRandom random = new SecureRandom();
        String randomKey = new BigInteger(130, random).toString(32);
        if (executionIdToTestsInProgressMap.keySet().contains(randomKey) || executionIdToMergedTestResultMap.contains(randomKey)) {
            randomKey = generateRandomKey();
        }
        return randomKey;
    }


}
