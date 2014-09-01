import com.expedia.aggregator.trx.StringAggregator;
import junit.framework.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dgaglani on 8/28/14.
 */
public class testStringAggregator {

    @Test
    public void testStringAggregator() throws Exception {
        String s1 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaamamamamamamamamamamamamamamamamamamamamamamamamamamama";
        String s2 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaamamamamamamamamamamamamamamamamamamamamamamamamamamama";
        String s3 = "x";
        String s4 = "y";
        StringAggregator stringAggregator = new StringAggregator();
        List<String> stringList = new ArrayList<String>();
        stringList.add(s1);
        stringList.add(s2);
        stringList.add(s3);
        stringList.add(s4);
        String result = stringAggregator.mergeElements(stringList);
        Assert.assertTrue("Result does not match, expected: "+ s1+","+s2+","+s3+","+s4 +", Actual " + result, result.equals(s1+","+s2+","+s3+","+s4));
    }
}
