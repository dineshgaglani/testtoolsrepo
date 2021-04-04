package interviewcamp.time1;

public class KthLargest {

    public Integer getKthLargest(Integer[] arr, Integer k) {
        return kthLargestHelper(arr, k - 1, 0, arr.length);
    }

    private Integer kthLargestHelper(Integer[] arr, Integer k, Integer start, Integer end) {
        int randomIndex = ((Double)(Math.random() * start + (end - start))).intValue();
        int pivotIndex = partition(arr, start, end, randomIndex);
        if (pivotIndex == k) {
            return arr[pivotIndex];
        } else if (pivotIndex > k) {
            return kthLargestHelper(arr, k, start, pivotIndex - 1);
        } else {
            return kthLargestHelper(arr, k - pivotIndex, pivotIndex + 1, end);
        }
    }

    private int partition(Integer[] arr, int start, int end, int randomIndex) {
        Util<Integer> util = new Util<>();
        util.swap(arr, randomIndex, start);
        int less = start + 1;
        for (int i = start + 1; i < end; i++) {
            if (arr[i] <= arr[start]) {
                util.swap(arr, i, less);
                less++;
            }
        }
        //MISTAKE - MISSED NEXT LINE
        util.swap(arr, start, less);
        return less;
    }
}
