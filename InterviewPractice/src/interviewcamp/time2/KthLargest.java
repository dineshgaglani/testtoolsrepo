package interviewcamp.time2;

import interviewcamp.time1.Util;

public class KthLargest {

    public Integer findKthLargest(Integer[] arr, Integer k) {
        return findKthLargest(arr, k - 1, 0, arr.length);
    }

    private Integer findKthLargest(Integer[] arr, Integer k, Integer start, Integer end) {
        Integer randomIndex = ((Double)(Math.random() * start + (end - start))).intValue();
        Integer pivotIndex = partition(arr, start, end, randomIndex);
        if (pivotIndex == k) {
            return arr[pivotIndex];
        } else if (pivotIndex > k) {
            return findKthLargest(arr, k, start, pivotIndex - 1);
        } else {
            return findKthLargest(arr, k - pivotIndex - 1, pivotIndex + 1, end);
        }
    }

    private Integer partition(Integer[] arr, Integer start, Integer end, Integer randomIndex) {
        Util<Integer> util = new Util<>();
        util.swap(arr, start, randomIndex);
        int less = start + 1;
        for (int i = start + 1; i < end; i++) {
            if (arr[i] <= arr[start]) {
                util.swap(arr, less, i);
                less++;
            }
        }
        util.swap(arr, start, less);
        return less;
    }


}
