package com.expedia.airawat.test.runner.test;

import com.expedia.airawat.test.executor.service.ExecutorServiceResource;
import com.expedia.airawat.test.executor.service.objects.TestExecutionRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dgaglani on 9/4/14.
 */
public class TestTestExecutorServiceResource {

    @Test
    public void testCreateTestExecution() throws Exception {
        //TODO - Complete test
        TestExecutionRequest request = new TestExecutionRequest();
        request.setTestRunnerType("shell");
        request.setResultFileType(".trx");
        List<List<String>> testList = new ArrayList<List<String>>();
        testList.add(Arrays.asList("regression,anothertest".split(",")));
        testList.add(Arrays.asList("secondTest".split(",")));
        testList.add(Arrays.asList("Third test,and now fourth"));
        request.setTestsList(testList);
        ObjectMapper mapper = new ObjectMapper();
        String requestString = mapper.writeValueAsString(request);
        /*ExecutorServiceResource resource = new ExecutorServiceResource();
        resource.createTestExecution(request);*/

    }
}
