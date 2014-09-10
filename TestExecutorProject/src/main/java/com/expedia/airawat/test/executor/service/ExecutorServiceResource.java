package com.expedia.airawat.test.executor.service;

import com.expedia.airawat.test.executor.engine.TestExecutor;
import com.expedia.airawat.test.executor.service.objects.TestExecutionRequest;
import com.expedia.airawat.test.executor.service.objects.TestExecutorResponse;

import javax.ws.rs.*;
import java.util.ArrayList;
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
        List<List<String>> testNames = executionRequest.getTestsList();
        String testRunnerType = executionRequest.getTestRunnerType();
        TestExecutor testExecutor = new TestExecutor();
        String randomKey = ServiceUtils.generateRandomKey();
        String executorLocation = ServiceUtils.getExecutorFilePath();
        if (executionRequest.getExecutorLocation() != null && !executionRequest.getExecutorLocation().isEmpty()) {
            executorLocation = executionRequest.getExecutorLocation();
        }
        String resultFileLocation = ServiceUtils.getMergedFileResultsPath(randomKey);
        if(executionRequest.getResultFileLocation() != null && !executionRequest.getResultFileLocation().isEmpty()) {
            resultFileLocation = executionRequest.getResultFileLocation();
        }
        try {
            new Thread(testExecutor.new RunnableExecutor(testRunnerType, testNames, executorLocation, resultsFileType, resultFileLocation)).start();
        } catch (Exception e) {

        }
        //Used to get the number of tests completed through tracker
        ServiceUtils.addToExecutionIdToTestExecutorMap(randomKey, testExecutor);
        //Used to get the total number of tests so that we can get the tests complete percent in subsequent requests
        ServiceUtils.addToTokenToNumberOfTestsMap(randomKey, ServiceUtils.getNumberOfTests(testNames));
        TestExecutorResponse response = new TestExecutorResponse();
        response.setTestRunId(randomKey);
        return response;
    }

    @Path("/json")
    @GET
    @Produces("application/json")
    public TestExecutorResponse getProgressForToken(@QueryParam("token") String token) {
        try {
            return ServiceUtils.getExecutedPercentageOrResultsFile(token);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Path("/tvaregression/json")
    @POST
    @Produces("application/json")
    public void runTVATests(TestExecutionRequest executionRequest) {
        TestExecutor testExecutor = new TestExecutor();
        String executorFilePath = ServiceUtils.getTVARegressionExecutorFilePath();
        if(executionRequest.getExecutorLocation() != null && !executionRequest.getExecutorLocation().isEmpty()) {
            executorFilePath = executionRequest.getExecutorLocation();
        }
        //Start the pre-execution step
        String preTestName = "PointToProd";
        List<String> preTestSet = new ArrayList<String>();
        preTestSet.add(preTestName);
        List<List<String>> preTestSuite = new ArrayList<List<String>>();
        preTestSuite.add(preTestSet);
        List<List<String>> testSuite = new ArrayList<List<String>>();
        List<String> testNamesSet1 = new ArrayList<String>();
        List<String> testNamesSet2 = new ArrayList<String>();

        testNamesSet1.add("OldSearchNewPrice");
        testNamesSet1.add("NewSearchOldPrice");
        testNamesSet1.add("FareRuleUseBFS");
        testNamesSet1.add("FareRuleMultiFBC");
        testNamesSet1.add("FareRuleToGDS");
        testNamesSet1.add("GetDetails");
        testNamesSet1.add("Verify");
        testNamesSet1.add("SearchV3GetDetailsV1");
        testNamesSet1.add("BFSUnsupportedSearchV4GetDetailsV2");
        testNamesSet1.add("SearchV4GetDetailsV2");
        testNamesSet1.add("OBFeeNotRequestedInGetDetailsRegressionTest");
        testNamesSet1.add("ProgressiveResponseRoundTrip");
        testNamesSet1.add("SingleResponseMultiDestination");
        testNamesSet1.add("ProgressiveResponseMultiDestination");
        testNamesSet1.add("BFSUnsupportedSerchV5Test");
        testNamesSet1.add("OBFeeGetDetailsRegressionTest");
        testNamesSet1.add("SingleResponseOneWayTrip");
        testNamesSet1.add("SingleResponseRoundTrip");
        testNamesSet1.add("ProgressiveResponseOneWayTrip");
        testNamesSet1.add("SearchV5WithOBFeeRegressionTest");
        testNamesSet1.add("SeatMap");
        testNamesSet1.add("CarrierSpecificSearchForNoSupportTF");
        testNamesSet1.add("TFMetroCodeSearchAndNoDPCPrice");
        testNamesSet1.add("TFMetroCodeSearchAndNoDPCPriceDepDifferArr");
        testNamesSet1.add("TFMetroCodeSearch");
        testNamesSet1.add("NoAirlineSpecificSearch");
        testNamesSet1.add("TFNoDPCinGetDetails");
        testNamesSet1.add("FlexMORTFRegressionTest");
        testNamesSet1.add("FlexMORAmadeusRegressionTest");
        testNamesSet1.add("BookingWithOBFeeRegressionTest");
        testNamesSet1.add("OMSBookV2Test_AmadeusTest");
        testNamesSet1.add("OMSBookV2Test_TravelPortTest");
        testNamesSet1.add("OMSBookV2TFTest");
        testNamesSet1.add("OMSBookV2Test_SabreTest");
        testNamesSet1.add("Book");
        testNamesSet1.add("BookOMS");
        testNamesSet1.add("TFRoundTrip");
        testNamesSet1.add("TFNoDPCinPurchase");
        testNamesSet1.add("TFStandalone");
        testNamesSet1.add("TFNoCVVBook");
        testNamesSet1.add("PriceChangeAtCompletePurchaseTest");
        testNamesSet1.add("PriceChangeTest");
        testNamesSet1.add("ScheduleChangeTest");

        testNamesSet2.add("CharterBookingTest");
        testNamesSet2.add("WorldspanMSTests");
        testNamesSet2.add("SabreMSTests");
        testNamesSet2.add("AmadeusMSTests");
        testNamesSet2.add("DirectTicketingMSTest");

        testSuite.add(testNamesSet1);
        testSuite.add(testNamesSet2);
        try {
            testExecutor.executeTests("shell", preTestSuite, executorFilePath + "preregressionexecutor.bat", ".trx", executionRequest.getResultFileLocation());
            //Execute tests 5 times, merged file location will be the place where .trx is being read by the tests
            for(int i = 0; i < 5; i++) {
                testExecutor.executeTests("shell", testSuite, executorFilePath + "regressionexecutor.bat", ".trx", executionRequest.getResultFileLocation());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
