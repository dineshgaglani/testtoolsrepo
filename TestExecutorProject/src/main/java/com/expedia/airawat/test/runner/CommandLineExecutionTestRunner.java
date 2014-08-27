package com.expedia.airawat.test.runner;

import org.apache.commons.exec.*;

import java.io.File;
import java.io.IOException;

/**
 * Created by dgaglani on 8/24/14.
 */
public class CommandLineExecutionTestRunner implements TestRunner {

    @Override
    public void executeTest(String executorFilePath, String testName, String testResultsFileName) {
        CommandLine cmdLine = new CommandLine(executorFilePath);
        cmdLine.addArgument(testName);
        cmdLine.addArgument(testResultsFileName);
        Executor executor = new DefaultExecutor();
        String workingDirectoryPath = executorFilePath.substring(0,executorFilePath.lastIndexOf(System.getProperty("file.separator")));
        executor.setWorkingDirectory(new File(workingDirectoryPath));
        executor.setExitValue(1);
        try {
            int retVal = executor.execute(cmdLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
