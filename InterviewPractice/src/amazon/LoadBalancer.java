package amazon;

/*
Given an array containing only positive integers, return if you can pick two integers from the array which cuts the array into three pieces such that the sum of elements in all pieces is equal.



Example 1:

Input:  array = [2, 4, 5, 3, 3, 9, 2, 2, 2]

Output: true

Explanation: choosing the number 5 and 9 results in three pieces [2, 4], [3, 3] and [2, 2, 2]. Sum = 6.



Example 2:

Input:  array =[1, 1, 1, 1],

Output: false
 */
public class LoadBalancer {

    public static boolean isLoadBalancePossible(Integer[] input) {
        boolean isLoadBalanced = false;
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (sum(input, 0, i) == sum(input, i + 1, j)
                        && sum(input, i + 1, j) == sum(input, j+1, input.length)) {
                    System.out.println("ith val = " + input[i]);
                    System.out.println("jth val = " + input[j]);
                    isLoadBalanced = true;
                    break;
                }
            }

        }
        return isLoadBalanced;
    }

    private static Integer sum(Integer[] input, int start, int end) {
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum = sum + input[i];
        }

        return sum;
    }

    public static void main (String args[]) {
        System.out.println(isLoadBalancePossible(new Integer[] {2, 4, 5, 3, 3, 9, 2, 2, 2} ));
    }
}
