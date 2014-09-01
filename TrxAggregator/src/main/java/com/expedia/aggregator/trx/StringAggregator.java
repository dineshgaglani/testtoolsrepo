package com.expedia.aggregator.trx;

/**
 * Created by dgaglani on 8/28/14.
 */
public class StringAggregator extends ParallelAggregator<String> {

    @Override
    public String performMerge(String elementToMerge1, String elementToMerge2) {
        return elementToMerge1 + "," + elementToMerge2;
    }
}
