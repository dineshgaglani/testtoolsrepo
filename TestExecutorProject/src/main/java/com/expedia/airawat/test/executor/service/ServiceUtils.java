package com.expedia.airawat.test.executor.service;

import com.expedia.airawat.test.executor.engine.TestExecutor;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dgaglani on 9/1/14.
 */
public class ServiceUtils {

    private static ConcurrentHashMap<String, TestExecutor> executionIdToTestsInProgressMap = new ConcurrentHashMap<String, TestExecutor>();
    private static ConcurrentHashMap<String, TestExecutor> executionIdToTestCompletedMap = new ConcurrentHashMap<String, TestExecutor>();

    public static void addToTestsInProgressMap(String token, TestExecutor testExecutor) {
        executionIdToTestsInProgressMap.put(token, testExecutor);
    }

    public static void addToTestsCompletedMap(String token, TestExecutor testExecutor) {
        executionIdToTestCompletedMap.put(token, testExecutor);
    }

    public static String getExecutedPercentageOrResultsFile(String token) throws Exception {
        int testCompleted = 0;
        if(executionIdToTestsInProgressMap.keySet().contains(token)) {
            TestExecutor executorInstance = executionIdToTestsInProgressMap.get(token);
            testCompleted = (executorInstance.getThreadsTracker().getCompletedThreadsCount() * 100)/(executionIdToTestsInProgressMap.size() + executionIdToTestCompletedMap.size());
            if(testCompleted == 100) {
                addToTestsCompletedMap(token, executorInstance);
                executionIdToTestsInProgressMap.remove(token);
            }
        }
        return testCompleted+"";
    }

    public static String getExecutorFilePath() {
        return "/Users/dgaglani/Desktop/tasks/Airawat/TestExecutorProject/src/main/resources/testBatchFile.sh";
    }

    public static String getMergedFileResultsPath() {
        return "";
    }

    public static String generateRandomKey() {
        SecureRandom random = new SecureRandom();
        String randomKey = new BigInteger(130, random).toString(32);
        if (executionIdToTestsInProgressMap.keySet().contains(randomKey) || executionIdToTestCompletedMap.contains(randomKey)) {
            randomKey = generateRandomKey();
        }
        return randomKey;
    }


}
