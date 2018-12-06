package sort;

public class KthLargest {

    /*
        Objective is to find the element that belongs to the kth position when sorted
        Approach: The quick sort algorithm essentially places a randomly chosen digit in its spot and then sorts the
        elements in the same way on either side of the randomly chosen digit.

        We will do a partial quick sort where we don't sort on both sides but only on the side where K is.

        Algorithm:
        Quicksort function: take in a start position, an end position and a target value, move end - 1 and start + 1
        until both cross
         compare start to target and end to target, stop when element at start bigger than end, stop at end when value
         is smaller, switch both and move both again until the event reoccurs, when the values cross put target at start + 1
         (if pointers crossed) or at start (if pointers did not cross).
         Return where we put the target
        If returned target < K pass in k+1 as start, if returned target > K pass in k-1 as end to the above function,
        if K is same as target then return value at K

        Mistakes: Quick sort algorithm the biggest confusion is after all swaps, where do we put the pivot, because
        when the start and end meet at a value bigger than the pivot, if we swap with start we will have moved a bigger
        value in front. If we swap with start - 1 and if start has exceeded end and end is at a bigger value the again
        if we swap with start - 1 we again have the same problem. The code here suffers from this issue

        In quick sort we call the pivot function directly, but in this we need to know which direction we need to move so
        we have an "if" to set the pivot value, the pivot value must be set to -1 because we set start to pivot + 1 and
        start comparing from start + 1 in the pivot compare function

        Again forgot to add the terminal conditions to the loop, if our less pointer sees that all are smaller than target
        then it will exceed the length of the array, so it has to be less than end, same with end

        Overall wrote pretty horrible code and need to practice a lot
     */
    public static int findKthLargest(Integer[] ints, int k, int start, int end) {
        int pivot = -1;

        while (pivot != k) {
            if (pivot < k) {
                start = pivot + 1;
            } else {
                if (pivot > -1) {
                    end = pivot - 1;
                }
            }
            pivot = getPivotPos(ints, start, end);
        }

        return ints[pivot];
    }

    private static int getPivotPos(Integer[] ints, int start, int end) {
        int target = ints[start];
        int l = start + 1;
        while (l < end) {
            while (ints[l] < target && l < end)
            { l++; }
            while (ints[end] >= target && end > l)
            { end--; }
            if(l < end) {
                swap(ints, l, end);
            }
        }
        swap(ints, start, end);
        return end;
    }

    private static void swap(Integer[] ints, int l, int end) {
        int temp = ints[l];
        ints[l] = ints[end];
        ints[end] = temp;
    }

    public static void main (String args[]) {
        //Test case for when start meets end and when they both cross
        Integer[] ints = new Integer[] { 3, 5, 2, 8, 9, 0, 4, 1};
        System.out.println(findKthLargest(ints, 6, 0, ints.length - 1));

        System.out.println(findKthLargest(ints, 3, 0, ints.length - 1));

        //Sorted list
        Integer[] sorted = new Integer[] { 0, 1, 3, 4, 5, 7, 8, 9 };
        System.out.println(findKthLargest(sorted, 5, 0, ints.length - 1));
    }
}
