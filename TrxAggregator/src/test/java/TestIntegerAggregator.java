import com.expedia.aggregator.trx.IntegerAggregator;
import com.expedia.aggregator.trx.ParallelAggregator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dgaglani on 8/26/14.
 */
public class TestIntegerAggregator {

    @Test
    public void testAggregateIntegers() throws Exception {
        List<Integer> integerList = new ArrayList<Integer>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(5);
        integerList.add(6);
        integerList.add(7);
        ParallelAggregator integerAggregator = new IntegerAggregator();
        Integer returnedValue = ((IntegerAggregator)integerAggregator).aggregateIntegers(integerList);
        Assert.assertTrue(returnedValue.equals(1234567), "Integers not merged correctly, expected: " + 1234567 + ", actual " + returnedValue);
    }
}
