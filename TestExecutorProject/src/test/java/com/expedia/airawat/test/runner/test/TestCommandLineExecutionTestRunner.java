package com.expedia.airawat.test.runner.test;

import com.expedia.airawat.test.runner.BatchTestRunner;
import com.expedia.airawat.test.runner.CommandLineExecutionTestRunner;
import com.expedia.airawat.test.runner.TestRunner;
import junit.framework.Assert;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Created by dgaglani on 8/24/14.
 */
public class TestCommandLineExecutionTestRunner {

    @Test
    public void testExecuteTest() {
        TestRunner testRunner = new CommandLineExecutionTestRunner();
        String executionFilePath = "/Users/dgaglani/Desktop/tasks/Airawat/TestExecutorProject/src/main/resources/";
        testRunner.executeTest(executionFilePath + "testBatchFile.sh", "regression", "regressionresult.trx");
        Assert.assertTrue("File " + executionFilePath + "regression.trx not found!!", new File(executionFilePath + "regressionresult").exists());
    }
}
