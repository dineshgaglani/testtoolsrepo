package dynamicprogramming;

/*
    First Assumption mistake:
    As we move ahead in the array if we keep track of the biggest element in the array we won't get the correct longest
    subsequence because the biggest element prior to current element may be the first element.

    If we know what the size of the longest subsequence was before this element and if this element is bigger than the
    largest element of the longest subsequence so far then the longest subsequence increases by 1.
    If this is smaller than the longest subsequence it could still be part of the longest sub sequence if there are elements
    ahead which are bigger than it.

    For an array with 3 nums, if in decreasing order then the longest subsequence is 1.
    If the first is bigger than the second, when we get to the third what should we comapare it with?
    With both first and second. So first insight is that every element at index i has to be compared with all elements in
    indexes smaller than it. If it is bigger than the element it is being compared to then we check if the element is part
    of a longer subsequence already, if yes we leave it as it is, if not then we increase the count by +1 of the index that
    the element we are comparing it with is in the longest subsequence chain. Concretely, we compare 3rd with 1st, its
    bigger so we look at where the 1st is in the longest chain, it's in the first, so we make the third elements longest
    chain as 1 + 1(longest chain until first element) so 2. And then we compare with 2, if bigger then we look at where 2nd
    is in the longest chain (in our scenario its the first element in the longest chain, so at 1 and we already have the
    longest chain at 3, so we leave it as it is)

    Algorithm:
    We will store the longest subsequence until 'i' at 'i' in a new array (of size input array)
    We will store the index of the previous element in the subsequence the current element falls in another array
    (Size of input array)

    Create lonSub and set all values to 1
    Create prevMap
    Start i loop from 0 to ipArrayLength
        Start j loop from 0 to i
            if (a[i] < a[j]) set lonSub[i] = Max(lonSub[j] + 1, lonSub[i]) set prevMap[i] = j
            //The values above keep changing (for the current element) as we iterate
*/

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class LongestIncreasingSubsequence {

    public static int longestIncreasingSubsequence(Integer[] ints) {
        Integer[] lSeq = new Integer[ints.length];
        Integer[] prevMap = new Integer[ints.length];

        for (int i = 0; i < lSeq.length; i++) {
            lSeq[i] = 1;
        }

        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < i; j++) {
                if (ints[j] < ints[i]) {
                    if (lSeq[j] + 1 > lSeq[i]) {
                        lSeq[i] = lSeq[j] + 1;
                        prevMap[i] = j;
                    }
                }
            }

        }
        return findMax(lSeq);
    }

    private static int findMax(Integer[] lSeq) {
        return Arrays.stream(lSeq).sorted().collect(Collectors.toList()).get(lSeq.length - 1);
    }

    public static void main (String args[]) {

        System.out.println(longestIncreasingSubsequence(new Integer[] { 10, 22, 9, 33, 21, 50, 41, 60, 80 } ));

    }


}
