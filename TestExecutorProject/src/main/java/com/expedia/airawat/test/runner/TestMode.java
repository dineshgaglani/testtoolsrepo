package com.expedia.airawat.test.runner;

/**
 * Created by dgaglani on 8/20/14.
 */
public interface TestMode<T> {

    /* returns the test results in the form the caller desires */
    public T runTest(TestRunner testRunner, String[] testNames, String executorFilePath, String testResultsFileNamePart);
}
