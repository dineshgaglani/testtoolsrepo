package com.arrays;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * Created by dgaglani on 9/18/14.
 */
public class VmwareQuestion {
    /*
    <p>You are given an array of size N. The elements of the array are d[0], d[1], ... d[N - 1], where each d[i] is either 0 or 1.&nbsp;You can perform at most&nbsp;one move on the array: choose any two integers&nbsp;[L, R], and flip all the elements between (and including) the L<sup>th</sup> and R<sup>th</sup> bits. L and R represent the left-most and the right-most index of the bits marking the boundaries of&nbsp;the segment which you have decided to flip. &nbsp;</p>

<p>&nbsp;</p>

<p>What is&nbsp;the maximum&nbsp;number of '1'-bits&nbsp;(indicated by S) which you can obtain in the final bit-string?&nbsp;'Flipping' a bit means, that a 0 is transformed to a 1 and a 1 is transformed to a 0.</p>

<p>&nbsp;</p>

<p><strong>Input Format:</strong><br>
A single integer N<br>
The next line contains the&nbsp;N elements in the array separated by a space: d[0] d[1] ... d[N - 1]</p>

<p>&nbsp;</p>

<p><strong>Output format:</strong><br>
Output a single integer that denotes the maximum number of 1-bits which can be obtained in the final bit string</p>

<p>&nbsp;</p>

<p><strong>Constraints:</strong><br>
1 &lt;= N &lt;= 100000<br>
d[i] can be either 0 or 1. It cannot be any other integer.</p>

<p>0 &lt;= L &lt;= R &lt; n</p>

<p>&nbsp;</p>

<p><strong>Sample Input:</strong><br>
8<br>
1 0 0 1 0 0 1 0</p>

<p>&nbsp;</p>

<p><strong>Sample Output:</strong><br>
6</p>

<p>&nbsp;</p>

<p><strong>Explanation:</strong><br>
We can get a maximum of&nbsp;6 ones in the given binary array by performing either of the following operations:<br>
Flip [1, 5] ==&gt; 1 1 1 0 1 1 1 0<br>
or&nbsp;<br>
Flip [1, 7] ==&gt; 1 1 1 0 1 1 0 1</p>

     */

    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        /*Scanner stdin = new Scanner(new BufferedInputStream(System.in));
        stdin.nextLine();
        String inputArray = stdin.nextLine();
        String[] integersAsStringArray = inputArray.split(" ");*/
        String[] integersAsStringArray = new String("1 0 0 1 0 0 1 0").split(" ");
        int maxZeroCount = 0;
        int zeroCountUptoNow = 0;
        int zeroStartPosition = 0;
        int maxZerosStartPosition = 0;
        int maxZeroEndPostion = 0;
        int maxZeros = 0;
        int onesCount = 0;
        int onesBetweenZeroStartAndMaxEnd = 0;
        boolean containsZeros = false;
        for(int i=0; i < integersAsStringArray.length; i++) {
            if (Integer.parseInt(integersAsStringArray[i]) == 0) {
                containsZeros = true;
            }
        }
        if(containsZeros) {
            for(int i=0; i < integersAsStringArray.length; i++) {
                if(Integer.parseInt(integersAsStringArray[i]) == 0) {
                    if(maxZeroCount == 0) {
                        zeroStartPosition = i;
                    }
                    zeroCountUptoNow++;
                    if(zeroCountUptoNow > maxZeroCount) {
                        maxZeroCount = zeroCountUptoNow;
                        maxZerosStartPosition = zeroStartPosition;
                        maxZeroEndPostion = i;
                    }
                }
                else {
                    //number is 1, reduce zero count
                    if(maxZeroCount != 0) {
                        zeroCountUptoNow--;
                    }
                }
            }
            //Flip
            int[] flippedIntArray = new int[integersAsStringArray.length];
            for(int i=0; i < integersAsStringArray.length; i++) {
                if(i >= maxZerosStartPosition && i <= maxZeroEndPostion) {
                    if(Integer.parseInt(integersAsStringArray[i]) == 0) {
                        flippedIntArray[i] = 1;
                    } else {
                        flippedIntArray[i] = 0;
                    }
                }
                else {
                    flippedIntArray[i] = Integer.parseInt(integersAsStringArray[i]);
                }
            }
            //count
            int count = 0;
            for(int i=0; i < flippedIntArray.length; i++) {
                System.out.print(flippedIntArray[i] + " ");
                if(flippedIntArray[i] == 1) {
                    count++;
                }
            }
            System.out.print("Zero start position : " + maxZerosStartPosition + ", max Zero end position : " + maxZeroEndPostion + ", count = "+count);
        }
        else {
            System.out.print(integersAsStringArray.length);
        }

    }
}
