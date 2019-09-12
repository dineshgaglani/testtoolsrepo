package LastMinutePrep;

import java.util.Arrays;

/**
 * Created by Dinesh on 9/8/2019.
 */
public class MergeSort {

    public void mergeSort(Integer[] input, Integer start, Integer end) {
        Integer mid = (start + end)/2;
        if (mid == start) {
            return;
        }
        mergeSort(input, start, mid);
        mergeSort(input, mid, end);
        Integer[] merged = merge((Integer[])Arrays.asList(input).subList(start, mid).toArray(), (Integer[])Arrays.asList(input).subList(mid + 1, end).toArray());
        int mergedCtr = 0;
        for (int i = start; i < end; i++) {
            input[i] = merged[mergedCtr];
            mergedCtr++;
        }
    }

    public Integer[] merge(Integer[] first, Integer[] second) {
        Integer[] merged = new Integer[first.length + second.length];
        int firstCtr = 0, secondCtr  = 0;
        for (int mergedCounter = 0; mergedCounter < merged.length; mergedCounter++) {
            if (firstCtr >= first.length) {
                merged[mergedCounter] = second[secondCtr];
                secondCtr++;
            }
            else if (secondCtr >= second.length) {
                merged[firstCtr] = first[firstCtr];
                firstCtr++;
            } else {
                if (first[firstCtr] < second[secondCtr]) {
                    merged[mergedCounter] = first[firstCtr];
                    firstCtr++;
                } else {
                    merged[mergedCounter] = second[secondCtr];
                    secondCtr++;
                }
            }
        }
        return merged;
    }
}
