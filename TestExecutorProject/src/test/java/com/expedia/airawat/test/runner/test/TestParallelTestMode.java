package com.expedia.airawat.test.runner.test;

import com.expedia.airawat.test.executor.engine.TestExecutor;
import com.expedia.airawat.test.runner.CommandLineExecutionTestRunner;
import com.expedia.airawat.test.runner.ParallelTestMode;
import com.expedia.airawat.test.runner.TestRunner;
import junit.framework.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
        //String[] testsToRun = "Regression,baingan:aggression:rompShomp,gattacurry,batatavada,plainchutney".split(":");
        String[] testsToRun = "Regression,gattacurry:aggression:rompShomp".split(":");
        List<List<String>> tests = new ArrayList();
        List<String> set1 = new ArrayList<String>();
        set1.add("rompShomp");
        tests.add(set1);
        List<String> set2 = new ArrayList<String>();
        set2.add("aggression2");
        set2.add("regression2");
        tests.add(set2);
        List<String> set3 = new ArrayList<String>();
        set3.add("Regression");
        set3.add("aggression");
        set3.add("rompShomp2");
        tests.add(set3);
        String scriptDirectorypath = "/Users/dgaglani/Desktop/tasks/Airawat/TestExecutorProject/src/main/resources/";
        TestExecutor.CompletedThreadsTracker completedThreadsTracker = new TestExecutor().new CompletedThreadsTracker();
        List<String> resultsFileList = parallelTestMode.runTest(commandLineExecutor, tests, scriptDirectorypath + "testBatchFile.sh", testTimeStamp, completedThreadsTracker);
        Assert.assertTrue("Threads tracker shows incomplete tests, expected: all tests completed, actual: " + completedThreadsTracker.getCompletedThreadsCount(), completedThreadsTracker.getCompletedThreadsCount() == 6);
        Assert.assertTrue("6 tests run, but the results does not have 6 files.. actual number of files " + resultsFileList.size(), resultsFileList.size() == 6);
        for(List<String> serialTestSet : tests) {
            for(String individualTest : serialTestSet) {
                Assert.assertTrue(" resulting files does not contain results for " + individualTest, resultsFileList.contains(scriptDirectorypath + testTimeStamp + "-" + individualTest));
                Assert.assertTrue(" File for test " + individualTest + " not created ", new File(scriptDirectorypath + testTimeStamp + "-" + individualTest).exists());
            }
        }
        System.out.print("Time taken: ");
        System.out.print(System.currentTimeMillis() - Long.parseLong(testTimeStamp));
        System.out.print("");
    }
}
