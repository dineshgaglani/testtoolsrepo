package interviewcamp.time4;

public class MergeSort {

    public Integer[] mergeHelper(Integer arr[]) {
        Integer mid = arr.length/2;
        if (mid == 0) {
            return new Integer[] {arr[mid]};
        }
        Integer[] l = new Integer[mid];
        for (int i = 0; i < mid; i++) {
            l[i] = arr[i];
        }

        Integer r[] = new Integer[arr.length - mid];
        int  rCtr = 0;
        for (int i = mid; i < arr.length - mid; i++) {
            r[rCtr] = arr[i];
            rCtr++;
        }

        Integer[] lSorted = mergeHelper(l);
        Integer[] rSorted = mergeHelper(r);

        return merge(lSorted, rSorted);
    }

    private Integer[] merge(Integer[] lSorted, Integer[] rSorted) {
        int lCtr = 0;
        int rCtr = 0;
        int mergedCtr = 0;
        Integer[] merged = new Integer[lSorted.length + rSorted.length];

        while (mergedCtr < merged.length) {
            if (lCtr >= lSorted.length ) { merged[mergedCtr] = rSorted[rCtr]; rCtr++; }
            else if (rCtr >= rSorted.length) {merged[mergedCtr] = lSorted[lCtr]; lCtr++; }
            else if (lSorted[lCtr] <= rSorted[rCtr]) { merged[mergedCtr] = lSorted[lCtr]; lCtr++; }
            else { merged[mergedCtr] = rSorted[rCtr]; rCtr++; }

            mergedCtr++;
        }

        return merged;
    }


}
