package hackerrank.newyearchaos;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {
        //Find the number that is smaller than the number at current index
        int count = 0;
        boolean chaotic = false;
        for (int i = 0; i < q.length; i++) {
            if ((q[i] - i) - 1 > 2) {
                chaotic = true;
                System.out.println("Too chaotic");
                break;
            }
            //As we move in the array we are scanning from the original location of the current element looking for items that exceed it in value
            System.out.println("Checking condition i: " + i + " Q[i] " + q[i] + " Math Max " + Math.max(0, q[i] - 2));
            for (int j = Math.max(0,(q[i] - 2)); j <= i; j++) {
                System.out.println("Looping for " + q[i] + " at " + i);
                if (q[j] > q[i]) {
                    count++;
                }
            }
        }


        if (!chaotic) {
            System.out.println(count);
        }

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[] q = {2, 1, 5, 3, 4};

        minimumBribes(q);
    }
}

