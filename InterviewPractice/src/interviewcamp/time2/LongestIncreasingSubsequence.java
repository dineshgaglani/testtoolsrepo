package interviewcamp.time2;

import java.util.Arrays;
import java.util.Comparator;

public class LongestIncreasingSubsequence {

    public static Integer findLongestIncreasingSubsequenceSize(Integer[] input) {
        Integer[] positionInIncreasingSequence = new Integer[input.length];
        Arrays.fill(positionInIncreasingSequence, 1);
        for (int i = 0; i < input.length; i++) {
            for (int j = i+1; j < input.length; j++) {
                if (input[i] < input[j]) {
                    if (positionInIncreasingSequence[j] < positionInIncreasingSequence[i] + 1) {
                        positionInIncreasingSequence[j] = positionInIncreasingSequence[i] + 1;
                    }
                }
            }
        }

        return Arrays.stream(positionInIncreasingSequence).max(Comparator.comparingInt(Integer::intValue)).get();
    }

    public static void main (String args[]) {
        System.out.println(findLongestIncreasingSubsequenceSize(new Integer[] {1, 3, 2, 5, 3, 5, 6, 4}) + " should equal 5 " +
                "because [1, 2, 3, 5, 6] is the longest increasing subsequence");
        System.out.println(findLongestIncreasingSubsequenceSize(new Integer[] {1, 0, 2, 5, 3, 5, 6, 4}) + " should equal 5 " +
                "because [1, 2, 3, 5, 6] is the longest increasing subsequence");
    }
}
