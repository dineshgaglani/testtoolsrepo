import com.expedia.aggregator.trx.ParallelAggregator;
import com.expedia.aggregator.trx.TrxAggregator;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dgaglani on 8/26/14.
 */
public class TestTrxAggregator {

    @Test
    public void testAggregateTrx() {
        File trxFile = new File("");
        File trxFile2 = new File("");
        List<File> trxFiles = new ArrayList<File>();
        trxFiles.add(trxFile);
        trxFiles.add(trxFile2);
        ParallelAggregator trxAggregator = new TrxAggregator();
     }
}
