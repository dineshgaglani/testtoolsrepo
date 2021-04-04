package interviewcamp.time1;

import java.util.Arrays;

/*
    { 5, 3, 7, 8, 5, 9, 6, 3, 7, 1 }

    Questions : A
    Args in recursive function - if it includes start and end, then what should the values be.
                                 If it passes the whole split array why does it need start and end, it can just return left half and right half
 */
public class MergeSort {

    public static Integer[] mergeSort(Integer[] ip) {
        return splitAndMerge(ip);
    }

    public static Integer[] splitAndMerge(Integer[] arr) {
        int mid = arr.length/2;
        if(mid == 0) {
            return new Integer[] {arr[mid]};
        }
        Integer[] l = new Integer[mid];
        int lCtr = 0;
        for (int i = 0; i < mid; i++) {
            l[lCtr] = arr[i];
            lCtr++;
        }
        Integer[] r = new Integer[arr.length - mid];
        int rCtr = 0;
        for (int i = mid; i < arr.length; i++) {
            r[rCtr] = arr[i];
            rCtr++;
        }

        Integer[] lSorted = splitAndMerge(l);
        Integer[] rSorted = splitAndMerge(r);

        return merge(lSorted, rSorted);
    }

    public static Integer[] merge (Integer[] l, Integer r[]) {
        Integer[] merged = new Integer[l.length + r.length];
        int ctr = 0;
        int rCtr = 0;
        int lCtr = 0;
        while (ctr < merged.length) {
            if (lCtr >= l.length) {merged[ctr] = r[rCtr]; rCtr++; }
            else if (rCtr >= r.length) {merged[ctr] = l[lCtr]; lCtr++; }
            else if (l[lCtr] <= r[rCtr]) { merged[ctr] = l[lCtr]; lCtr++; }
            else { merged[ctr] = r[rCtr]; rCtr++; }
            ctr++;
        }

        return merged;
    }

    public static void main (String args[]) {
        System.out.println(Arrays.asList(mergeSort(new Integer[] { 5, 3, 7 }) ));
        System.out.println(Arrays.asList(mergeSort(new Integer[] { 5, 3, 7, 8, 5, 9, 6, 3, 7, 1 } )));

//        System.out.println(Arrays.asList(merge(new Integer[] {5}, new Integer[] {3, 7})));
    }

}
