package com.expedia.airawat.test.runner.test;

import com.expedia.airawat.test.runner.CommandLineExecutionTestRunner;
import com.expedia.airawat.test.runner.ParallelTestMode;
import com.expedia.airawat.test.runner.TestRunner;
import junit.framework.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

/**
 * Created by dgaglani on 8/24/14.
 */
public class TestParallelTestMode {

    @Test
    public void testRunTest() {
        ParallelTestMode parallelTestMode = new ParallelTestMode();
        TestRunner commandLineExecutor = new CommandLineExecutionTestRunner();
        String testTimeStamp = new Long(System.currentTimeMillis()).toString();
        String[] testsToRun = "Regression,aggression,rompShomp".split(",");
        String scriptDirectorypath = "/Users/dgaglani/Desktop/tasks/Airawat/TestExecutorProject/src/main/resources/";
        List<String> resultsFileList = parallelTestMode.runTest(commandLineExecutor, "Regression,aggression,rompShomp".split(","), scriptDirectorypath + "testBatchFile.sh", testTimeStamp);
        Assert.assertTrue("3 tests run, but the results does not have 3 files.. actual number of files " + resultsFileList.size(), resultsFileList.size() == 3);
        for(String testName : testsToRun) {
            Assert.assertTrue(" resulting files does not contain results for " + testName, resultsFileList.contains(scriptDirectorypath + testTimeStamp + "-" + testName));
            Assert.assertTrue(" File for test " + testName + " not created ", new File(scriptDirectorypath + testTimeStamp + "-" + testName).exists());
        }
        System.out.print("Time taken: ");
        System.out.print(System.currentTimeMillis() - Long.parseLong(testTimeStamp));
        System.out.print("");
    }
}
