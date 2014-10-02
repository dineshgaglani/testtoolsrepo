package com.expedia.airawat.test.executor.service;

import com.expedia.aggregator.trx.TrxAggregator;
import com.expedia.airawat.test.executor.engine.TestExecutor;
import com.expedia.airawat.test.executor.service.configs.Paths;
import com.expedia.airawat.test.executor.service.objects.TestExecutionRequest;
import com.expedia.airawat.test.executor.service.objects.TestExecutorResponse;

import javax.ws.rs.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dgaglani on 8/31/14.
 */
@Path("/testexecutorservice")
public class ExecutorServiceResource {

    private Paths paths;

    public ExecutorServiceResource(Paths paths) {
        this.paths = paths;
    }

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
        TrxAggregator trxAggregator = new TrxAggregator();
        if(executionRequest.getExecutorLocation() != null && !executionRequest.getExecutorLocation().isEmpty()) {
            executorFilePath = executionRequest.getExecutorLocation();
        }
        //Start the pre-execution step
        String preTestName = "PointToProd";
        List<String> preTestSet = new ArrayList<String>();
        preTestSet.add(preTestName);
        List<List<String>> preTestSuite = new ArrayList<List<String>>();
        preTestSuite.add(preTestSet);
        List<List<String>> regressionTestSuite = new ArrayList<List<String>>();
        List<List<String>> purchaseTestSuite = new ArrayList<List<String>>();

        //Add the test sets for the corresponding .dlls
        List<String> regressionTests = new ArrayList<String>();
        regressionTests.add("SeatMap");
        regressionTests.add("FareRuleToGDS");
        regressionTests.add("FareRuleUseBFS");
        regressionTests.add("FareRuleMultiFBC");
        if(paths.isRunCharterForRegression()) {
            regressionTests.add("CharterBookingTest");
        }
        regressionTests.add("NewSearchOldPrice");
        regressionTests.add("TFMetroCodeSearch");
        regressionTests.add("CarrierSpecificSearchForNoSupportTF");
        regressionTests.add("TFMetroCodeSearchAndNoDPCPriceDepDifferArr");
        regressionTests.add("NoAirlineSpecificSearch");
        regressionTests.add("TFNoDPCinGetDetails");
        regressionTests.add("SearchV4GetDetailsV2");
        regressionTests.add("SearchV3GetDetailsV1");
        regressionTests.add("TFMetroCodeSearchAndNoDPCPrice");
        regressionTestSuite.add(regressionTests);

        List<String> regressionTests2 = new ArrayList<String>();
        regressionTests2.add("OldSearchNewPrice");
        regressionTests2.add("BFSUnsupportedSerchV5Test");
        regressionTests2.add("BFSUnsupportedSerchV5Test");
        regressionTests2.add("Verify");
        regressionTests2.add("GetDetails");
        regressionTests2.add("BFSUnsupportedSearchV4GetDetailsV2");
        //Not running in parallel
        regressionTests.add("OMSBookV2TFTest");
        regressionTests.add("Book");
        regressionTests2.add("OMSBookV2Test_SabreTest");
        regressionTests2.add("OMSBookV2Test_TravelPortTest");
        regressionTests2.add("OMSBookV2Test_AmadeusTest");
        regressionTests2.add("BookOMS");
        regressionTestSuite.add(regressionTests2);
        List<String> regressionTests3 = new ArrayList<String>();
        regressionTests3.add("SabreMSTests");
        regressionTests3.add("AmadeusMSTests");
        regressionTests3.add("WorldspanMSTests");
        regressionTests3.add("DirectTicketingMSTest");
        regressionTests3.add("TFRoundTrip");
        regressionTests3.add("TFStandalone");
        regressionTests3.add("TFRoundTrip");
        regressionTests3.add("TFNoDPCinPurchase");
        regressionTests3.add("TFNoCVVBook");
        regressionTests3.add("PriceChangeTest");
        regressionTests3.add("ScheduleChangeTest");
        regressionTests3.add("PriceChangeAtCompletePurcaseTest");

        regressionTestSuite.add(regressionTests3);

        List<String> purchaseTests1 = new ArrayList<String>();
        purchaseTests1.add("BookingWithOBFeeRegressionTest");
        purchaseTests1.add("FlexMORAmadeusRegressionTest");
        purchaseTestSuite.add(purchaseTests1);

        List<String> obFeeTests = new ArrayList<String>();
        obFeeTests.add("Searchv5withOBFeeRegressionTest");
        obFeeTests.add("OBFeeNotRequestedInGetDetailsRegressionTest");
        obFeeTests.add("OBFeeGetDetailsRegressionTest");
        List<List<String>> obFeeTestSuite = new ArrayList<List<String>>();
        obFeeTestSuite.add(obFeeTests);

        List<String> fppTests = new ArrayList<String>();
        fppTests.add("SingleResponseOneWayTrip");
        fppTests.add("SingleResponseRoundTrip");
        fppTests.add("ProgressiveResponseRoundTrip");
        fppTests.add("ProgressiveResponseMultiDestination");
        fppTests.add("ProgressiveResponseOneWayTrip");
        fppTests.add("SingleResponseMultiDestination");
        List<List<String>> fppTestSuite = new ArrayList<List<String>>();
        fppTestSuite.add(fppTests);

        try {
            if(paths.isRunPreProdForRegression()) {
                testExecutor.executeTests("shell", preTestSuite, executorFilePath + "preregressionexecutor.bat", ".trx", executionRequest.getResultFileLocation());
            }

            String placeWhereAggregatedTRXFilesGo = paths.getPathsWhereTrxGo();
            //Execute tests 5 times, merged file location will be the place where .trx is being read by the tests
            for(int i = 0; i < 5; i++) {
                testExecutor.executeTests("shell", regressionTestSuite, executorFilePath + "regressionexecutor.bat", ".trx", executorFilePath + "regressionresult"+i);
                testExecutor.executeTests("shell", purchaseTestSuite, executorFilePath + "purchaseexecutor.bat", ".trx", executorFilePath + "purchaseresult"+i);
                testExecutor.executeTests("shell", obFeeTestSuite, executorFilePath + "obfeetestexecutor.bat", ".trx", executorFilePath + "obfeetesresult"+i);
                testExecutor.executeTests("shell", fppTestSuite, executorFilePath + "fpptestexecutor.bat", ".trx", executorFilePath + "fpptesresult"+i);
                trxAggregator.aggregateTrxFromFileList(Arrays.asList(new String[]{executorFilePath + "regressionresult" + i, executorFilePath + "purchaseresult" + i, executorFilePath + "obfeetesresult" + i, executorFilePath + "fpptesresult" + i}), placeWhereAggregatedTRXFilesGo + i + ".trx");
            }
            ServiceUtils.sendEMail(placeWhereAggregatedTRXFilesGo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
