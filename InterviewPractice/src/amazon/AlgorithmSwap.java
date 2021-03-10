package amazon;

/*
You're a new Amazon Software Development Engineer (SDE). You're reading through your team's code and find an old sorting algorithm. The following algorithm is used to sort an array of distinct n integers:

For the input array arr of size n do:
Try to find the smallest pair of indices 0 <= i < j <= n-1 such that arr[i] > arr[j]. Here smallest means usual alphabetical ordering of pairs, i.e. (i1, j1) < (i2, j2) if and only if i1 < i2 or (i1 = i2 and j1 <j2).
If there is no such pair, stop.
Otherwise, swap a[i] and a[j] and repeat finding the next pair.
The algorithm seems to be correct, but the question is how efficient is it? Write a function that returns the number of swaps performed by the above algorithm.

For example, if the initial array is [5,1,4,2], then the algorithm first picks pair (5,1) and swaps it to produce array [1,5,4,2]. Next, it picks pair (5,4) and swaps it to produce array [1,4,5,2]. Next, pair (4,2) is picked and swapped to produce array [1,2,5,4], and finally, pair (5,4) is swapped to produce the final sorted array [1,2,4,5], so the number of swaps performed is 4.

Function Description
Complete the function howManySwaps in the editor below. The function should return an integer that denotes the number of swaps performed by the proposed algorithm on the input array.

The function has the following parameter(s):
arr: integer array of size n with all unique elements

Constraints
1 <= n <= 10^5
1 <=  arr[i] <= 10^9
all elements of arr are unique


Input Format Format for Custom Testing
Input from stdin will be processed as follows and passed to the function.
In the first line, there is a single integer n.
In the i-th of the next n lines, there is a single integer arr[i].

Sample Case
Sample Input
3
7
1
2

Sample Output
2

Explanation
There are 3 elements in the array, 7, 1 and 2 respectively.

Initially, there are two pairs of indices i < j for which a[i] > a[j]. These pairs are (0, 1) and (0, 2). Since (0, 1) is smaller of them, the algorithm swaps elements a[0] and a[1]. The resulting array is [1, 7, 2].
Next, in the second iteration there is only a single pair of indices i < j for which a[i> a[j]. This pair is (1, 2) and the algorithm swaps a[1] with a[2]. The resulting array is [1, 2, 7].
After that, the algorithm tries to find the next pair of indices to swap but since there is none, the algorithm stops.
The number of swaps it performed is 2.
 */

public class AlgorithmSwap {

    public static Integer findSwaps(Integer[] arr) {
        int swaps = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;

                    swaps++;
                }
            }
        }
        return swaps;
    }

    public static void main (String args[]) {
        System.out.println(findSwaps(new Integer[] {5, 1, 4, 2}));
        System.out.println(findSwaps(new Integer[] {7, 1, 2}));
    }
}
