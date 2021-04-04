package interviewcamp.time1;

import java.util.Arrays;

/*
(Level: Easy) Given an array of positive integers, find the contiguous subarray that sums to a given number X.

For example, input = [1,2,3,5,2] and X=8, Result = [3,5]

[3,7,6,2] target 8
{5,3,1,7,6,4,2,3} target 14

{3,7,6,2} target 8

Mistakes : 1. Incrementing/decrementing counter and THEN accessing values! This fails because after incrementing/decrementing
the pointer may have exceeded the size of the array or may have become -1
Mistake 2: Incrementing after adding/subtracting gives wrong range in result, since we reach the target and then increment
 */
public class SlidingWindowUsing2Pointers {

    public static Integer[] findRangeForTarget(Integer[] arr, Integer target) {
        int slow = 0;
        int fast = 0;
        int currSum = arr[0];
        boolean found = false;
        while (fast < arr.length) {
            if (currSum < target) {
//                currSum = currSum + arr[fast]; <Mistake>
                fast++;
                //lines 30 to 34 <correction>
                if (fast == arr.length) {
                    break;
                }
                currSum = currSum + arr[fast];
                //<end-correction>
            } else if (currSum > target) {
                currSum = currSum - arr[slow];
                slow++;
                if (slow > fast) {
                    fast++;
                    currSum = arr[fast];
                }
            } else {
                found = true;
                break;
            }
        }

        Integer[] ret = new Integer[fast - slow + 1];
        int c = 0;
        if(found) {
            for (int i = slow; i <= fast; i++) {
                ret[c] = arr[i];
                c++;
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        //System.out.println(Arrays.asList(findRangeForTarget(new Integer[] {1,2,3,5,2}, 8)) + "Should equal : [3, 5]");
        //System.out.println(Arrays.asList(findRangeForTarget(new Integer[] {5,3,1,7,6,4,2,3}, 14)) + "Should equal : [1,7,6]");
        System.out.println(Arrays.asList(findRangeForTarget(new Integer[] {3,7,6,2}, 8)) + "Should equal : [6,2]");
    }
}
