package com.expedia.aggregator.trx;

import java.util.List;

/**
 * Created by dgaglani on 8/26/14.
 */
public class IntegerAggregator extends ParallelAggregator<Integer> {

    @Override
    public Integer performMerge(Integer elementToMerge1, Integer elementToMerge2) {
        return Integer.parseInt(elementToMerge1 + "" + elementToMerge2);
    }

    public Integer aggregateIntegers(List<Integer> integerList) {
        return mergeElements(integerList);
    }
}
