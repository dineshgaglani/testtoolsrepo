package com.expedia.airawat.test.runner;

import java.io.IOException;

/**
 * Created by dgaglani on 8/24/14.
 */
public class ShellTestRunner implements TestRunner {

    public Process getRuntime(String shellFilePath, String testName) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        try {
            return processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void executeTest(String executorFilePath, String testName, String testResultsFileName) {
        getRuntime(executorFilePath, testName);
    }
}
