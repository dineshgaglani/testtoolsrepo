package com.expedia.airawat.test.executor.service;

import com.expedia.airawat.test.executor.engine.TestExecutor;
import com.expedia.airawat.test.executor.service.objects.TestExecutionRequest;
import com.expedia.airawat.test.executor.service.objects.TestExecutorResponse;
import com.sun.jersey.core.header.MediaTypes;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by dgaglani on 8/31/14.
 */
@Path("/testexecutorservice")
public class ExecutorServiceResource {

    @Path("/json")
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public TestExecutorResponse createTestExecution(TestExecutionRequest executionRequest) {
        String resultsFileType = executionRequest.getResultFileType();
        List<String> testNames = executionRequest.getTestsList();
        String testRunnerType = executionRequest.getTestRunnerType();
        TestExecutor testExecutor = new TestExecutor();
        String randomKey = ServiceUtils.generateRandomKey();
        try {
            new Thread(testExecutor.new RunnableExecutor(testRunnerType, testNames, ServiceUtils.getExecutorFilePath(), resultsFileType, ServiceUtils.getMergedFileResultsPath())).start();
        } catch (Exception e) {

        }
        ServiceUtils.addToTestsInProgressMap(randomKey, testExecutor);
        TestExecutorResponse response = new TestExecutorResponse();
        response.setTestRunId(randomKey);
        return response;
    }

    @Path("/json")
    @GET
    @Produces("application/xml")
    public TestExecutorResponse getProgressForToken(@QueryParam("token") String token) {
        String percentComplete = "0";
        try {
             percentComplete = ServiceUtils.getExecutedPercentageOrResultsFile(token);
        } catch(Exception e) {
            e.printStackTrace();
        }
        TestExecutorResponse response = new TestExecutorResponse();
        response.setCompletePercent(percentComplete);
        return response;
    }

}
