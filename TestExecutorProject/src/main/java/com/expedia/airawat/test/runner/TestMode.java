package com.expedia.airawat.test.runner;

import com.expedia.airawat.test.executor.engine.TestExecutor;

import java.util.List;

/**
 * Created by dgaglani on 8/20/14.
 */
public interface TestMode<T> {

    /* returns the test results in the form the caller desires */
    public T runTest(TestRunner testRunner, List<String> testNames, String executorFilePath, String testResultsFileNamePart, TestExecutor.CompletedThreadsTracker threadsTracker);
}
