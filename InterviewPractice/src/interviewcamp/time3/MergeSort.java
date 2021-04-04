package interviewcamp.time3;

public class MergeSort {

    public Integer[] mergeSort(Integer[] arr) {
        return mergeHelper(arr);
    }

    /*
        First we split with l from 0 to mid and from mid to end, then we recurse to split further,
        first recursing is WRONG since we need left to be sorted when the recursion returns.
        Set the return of the recursion to a new array
     */
    private Integer[] mergeHelper(Integer[] arr) {
        int mid = arr.length/2;
        if (mid == 0) { return new Integer[] {arr[mid]}; }
        Integer[] l = new Integer[mid];
        Integer[] r = new Integer[arr.length - mid];

        int lCtr = 0;
        for (int i = 0; i < mid; i++) {
            l[lCtr] = arr[i];
            lCtr++;
        }
        int rCtr = 0;
        for (int i = mid; i < arr.length; i++) {
            r[rCtr] = arr[i];
            rCtr++;
        }

        Integer[] lsorted = mergeHelper(l);
        Integer[] rsorted = mergeHelper(r);

        return merge(lsorted, rsorted);
    }

    //1,4,2
    //Questions - terminal condition ?? terminal condition return statement
    //When to call recursively? Size of left and right array
    //WRONG IMPLEMENTATION
    /*private Integer[] mergeHelper(Integer[] arr, int start, int end) {
        if (start >= end) {
            return new Integer[] {};
        }
        int mid = (end + start)/2;
        Integer[] l = mergeHelper(arr, start, mid);
        Integer[] r = mergeHelper(arr, mid, end);

        int lCtr = 0;
        for (int i = start; i <= mid; i++) {
            l[lCtr] = arr[i];
            lCtr++;
        }


    }*/

    public Integer[] merge(Integer[] l, Integer[] r) {
        Integer[] merged = new Integer[l.length + r.length];
        int mergedCtr = 0;
        int lctr = 0;
        int rctr = 0;

        while (mergedCtr < merged.length) {
            if (lctr >= l.length) { merged[mergedCtr] = r[rctr]; rctr++; }
            else if (rctr >= r.length) {merged[mergedCtr] = l[lctr]; lctr++; }
            else if (l[lctr] < rctr) {merged[mergedCtr] = l[lctr]; lctr++;}
            else {merged[mergedCtr] = r[rctr]; rctr++;}
            mergedCtr++;
        }

        return merged;
    }
}
