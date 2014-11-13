package com.Recursion;

/**
 * Created by dgaglani on 5/23/14.
 */
public class KNumbersThatAddUptoN {

    static int sum=0;

    public static int kNumbersThatAddUptoN(int N, int k) {
        //the greatest number in the set will be N - k and the rest of the numbers will be sum of k - 1 numbers that add up to k
        /*
             example : n = 15, k = 3
             1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15
             so anything greater than 12 will not add up because 13 + the least numbers will exceed 15(13 + 2 + 1)
             so first number is always N - k and now that we have the first number in the first set, among the other numbers (1 to k) we need to get k - 1 numbers that add up to k
             after this we increase the number that we need to subtract from n by 1 and(15 - (3 + 1)) and find all k - 1 numbers that add upto k + 1(meaning 2 numbers from 1 to 4 that add upto 4 in this case)
         */
        if( k == 1) {
            System.out.print(N + "\t");
            return N;
        }
        for(int i = 0; i < N - 1; i++) {
            System.out.print((N - k - i) + "\t");
            sum = (N - k - i) + kNumbersThatAddUptoN(k + i, k - 1);
        }
        System.out.println();
        return sum;
    }

    public static void main(String args[]) {
        kNumbersThatAddUptoN(15, 4);
    }
}
