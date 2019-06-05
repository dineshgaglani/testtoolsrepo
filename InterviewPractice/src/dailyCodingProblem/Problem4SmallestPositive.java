package dailyCodingProblem;

/*
    Given an array of integers, find the first missing positive integer in linear time and constant space. In other words, find the lowest positive integer that does not exist in the array. The array can contain duplicates and negative numbers as well.

    For example, the input [3, 4, -1, 1] should give 2. The input [1, 2, 0] should give 3.

    You can modify the input array in-place.

    Approach:
    Principle is that the index is always positive, so if we fill the positive values in their index then if
    an index it's corresponding value, that is the number that is missing
    Algorithm: Count the negatives in the first pass, start a counter at 0 (call it negativesCtr) and another a lenNegatives
    iterate from lenNegatives to lengthOfInputArray. On encountering a negative, move it to the front of the list and increment
    negativesCtr, on encountering a positive swap it with whatever is in it's index on the array and move ahead when

    Trick: There are 3 counters, i, integerCounter and negativesCtr. Synchronizing their movements is involved

 */

public class Problem4SmallestPositive {

    public static Integer findSmallestPositive (Integer[] input) {
        Integer negativesAndExcessNum = countNegativesAndExcess(input);
        Integer negativesAndExcessCtr = 0;

        for (int i = 0; i < input.length; i++) {
            if (input[i] < 0 || input[i] > input.length - negativesAndExcessNum) {
                swap(input, negativesAndExcessCtr, i);
                negativesAndExcessCtr++;
            }
        }

        //At this point negativesCounter should be equal to negativesNums
        System.out.println("Negatives and excesses moved aside: negativesExcessesCtr: " + negativesAndExcessCtr
                + ", num negatives: " + negativesAndExcessNum
                  +  ", these values should be equal");

        for (int i = negativesAndExcessNum; i < input.length; i++) {
            while (input[i] != i - negativesAndExcessNum) {
                swap(input, input[i], i - negativesAndExcessNum);
            }
        }

        for (int i = negativesAndExcessNum; i < input.length; i++) {
            if (input[i] != i - negativesAndExcessNum) {
                return i;
            }
        }
        return -1;
    }

    private static void swap(Integer[] input, Integer swapPos1, int swapPos2) {
        int temp = input[swapPos1];
        input[swapPos1] = input[swapPos2];
        input[swapPos2] = temp;
    }

    public static Integer countNegativesAndExcess (Integer[] input) {
        Integer negativesAndExcessNum = 0;
        for (int i = 0 ; i < input.length; i++) {
            if (input[i] < 0 || input[i] > input.length) {
                negativesAndExcessNum++;
            }

        }
        return negativesAndExcessNum;
    }

    public static void main (String[] args) {
        System.out.println(findSmallestPositive(new Integer[]{3, 4, -1, 1}));
        System.out.println("Should equal: 2");

        System.out.println(findSmallestPositive(new Integer[]{1, 2, 0}));
        System.out.println("Should equal: 3");
    }

}
