import com.expedia.aggregator.data.ObjectFactory;
import com.expedia.aggregator.data.TestRunType;
import com.expedia.aggregator.trx.ParallelAggregator;
import com.expedia.aggregator.trx.TrxAggregator;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dgaglani on 8/26/14.
 */
public class TestTrxAggregator {

    @Test
    public void testAggregateTrx() throws Exception {
        String folderPath = "/Users/dgaglani/Desktop/tasks/Airawat/TrxAggregator/src/test/resources/";
        String mergedFileLocation = folderPath + "mergedTrx.trx";
        TrxAggregator trxAggregator = new TrxAggregator();
        trxAggregator.aggregateTrxFiles(Utils.getTrxFilesFromFolder(folderPath), mergedFileLocation);
     }

    @Test
    public void testPerformMerge() throws Exception {
        JAXBElement<TestRunType> testRunTypeElement1 = ((JAXBElement<TestRunType>) JAXBContext.newInstance(TestRunType.class).createUnmarshaller().unmarshal(new File("/Users/dgaglani/Desktop/tasks/Airawat/TrxAggregator/src/test/resources/dgaglani_DEL507A1EA296 2014-08-13 18_43_42.trx")));
        TestRunType testRunTypeMergedResult = testRunTypeElement1.getValue();
        //The copy will contain the original testRunType, since the testRunType will change in the next lines we need a copy of the original
        TestRunType testRunTypePreMerge = ((JAXBElement<TestRunType>) JAXBContext.newInstance(TestRunType.class).createUnmarshaller().unmarshal(new File("/Users/dgaglani/Desktop/tasks/Airawat/TrxAggregator/src/test/resources/dgaglani_DEL507A1EA296 2014-08-13 18_43_42.trx"))).getValue();
        JAXBElement<TestRunType> testRunTypeElement2 = ((JAXBElement<TestRunType>)JAXBContext.newInstance(TestRunType.class).createUnmarshaller().unmarshal(new File("/Users/dgaglani/Desktop/tasks/Airawat/TrxAggregator/src/test/resources/dgaglani_DEL507A1EA296 2014-08-26 16_48_16.trx")));
        TestRunType testRunTypeToMerge = testRunTypeElement2.getValue();
        TrxAggregator trxAggregator = new TrxAggregator();
        trxAggregator.performMerge(testRunTypeMergedResult, testRunTypeToMerge);
        TrxValidators trxValidators = new TrxValidators(testRunTypeMergedResult, testRunTypeToMerge, testRunTypePreMerge);
        trxValidators.validateCounters();
        trxValidators.validateTestDefinition();
        trxValidators.validateTestEntries();
        trxValidators.validateUnitTestResults();
    }
}
