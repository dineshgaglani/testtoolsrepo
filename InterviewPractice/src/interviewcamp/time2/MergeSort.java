package interviewcamp.time2;

import java.util.Arrays;

public class MergeSort {

    public static Integer[] mergeSort(Integer[] ip) {

        if (ip.length == 1) {
            return ip;
        }

        int mid = ip.length/2;
        Integer[] l = new Integer[mid];
        for (int i = 0; i < mid; i++) {
            l[i] = ip[i];
        }
        Integer[] r = new Integer[ip.length - mid];
        for (int i = mid; i < ip.length; i++) {
            r[i - mid] = ip[i];
        }

        Integer[] lSorted = mergeSort(l);
        Integer[] rSorted = mergeSort(r);

        return merged(lSorted, rSorted);
    }

    public static Integer[] merged (Integer[] left, Integer right[]) {
        int lCtr = 0;
        int rCtr = 0;
        int ctr = 0;

        Integer[] res = new Integer[left.length + right.length];
        while (ctr < res.length) {
            if (lCtr >= left.length) { res[ctr] = right[rCtr]; rCtr++; }
            else if (rCtr >= right.length) { res[ctr] = left[lCtr]; lCtr++; }
            else if (left[lCtr] < right[rCtr]) { res[ctr] = left[lCtr]; lCtr++; }
            else {res[ctr] = right[rCtr]; rCtr++;}
            ctr++;
        }

        return res;
    }

    public static void main (String args[]) {
        System.out.println(Arrays.asList(mergeSort(new Integer[] { 5, 3, 7 }) ));
        System.out.println(Arrays.asList(mergeSort(new Integer[] { 5, 3, 7, 8, 5, 9, 6, 3, 7, 1 } )));

//        System.out.println(Arrays.asList(merge(new Integer[] {5}, new Integer[] {3, 7})));
    }
}
