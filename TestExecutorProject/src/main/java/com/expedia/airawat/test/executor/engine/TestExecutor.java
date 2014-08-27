package com.expedia.airawat.test.executor.engine;

import com.expedia.airawat.test.runner.ParallelTestMode;
import com.expedia.airawat.test.runner.TestMode;
import com.expedia.airawat.test.runner.TestRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by dgaglani on 8/20/14.
 */
@ContextConfiguration( locations={"classpath:application-context.xml"} )
public class TestExecutor {

    @Autowired
    private Environment env;

    public static void main(String args[]) {
        TestExecutor testExecutor = new TestExecutor();
        String[] argsString = args[0].split(" ");
        String testType = argsString[0];
        String[] testNames = argsString[1].split(",");
        String executorFilePath = argsString[2];
        TestRunner testRunner = testExecutor.getTestRuntime(testType);
        String testExecutionMode = testExecutor.env.getProperty("executionMode");
        TestMode testMode = null;
        if(testExecutionMode.equals("Parallel")) {
            testMode = new ParallelTestMode();
        } else if (testExecutionMode.equals("Serial")) {

        }

    }

    private TestRunner getTestRuntime(String testType) {
        //Get the class for the corresponding test-type from the property file
        String runnerImplForTestType = env.getProperty(testType);
        try{
            return (TestRunner)Class.forName(runnerImplForTestType).newInstance();
        }
        catch(ClassNotFoundException e) {

        }
        catch(IllegalAccessException e) {

        }
        catch (InstantiationException e) {

        }
        return null;
    }

}
