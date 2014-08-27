package com.expedia.airawat.test.runner;

import org.omg.SendingContext.RunTime;

/**
 * Created by dgaglani on 8/20/14.
 */
public class BatchTestRunner implements TestRunner {


    @Override
    public void executeTest(String executorFilePath, String testName, String testResultsFileName) {
        try {
            Runtime.getRuntime().exec("cmd.exe /c cd \"" + executorFilePath + "\" " + testName);
        } catch(Exception e) {

        }

    }
}
