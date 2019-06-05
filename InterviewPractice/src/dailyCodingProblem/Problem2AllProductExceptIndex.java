package dailyCodingProblem;

import java.util.Arrays;

/*
Given an array of integers, return a new array such that each element at index i of the new array is the product of all the numbers in the original array except the one at i.

For example, if our input was [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24]. If our input was [3, 2, 1], the expected output would be [2, 3, 6].

Follow-up: what if you can't use division?
 */
public class Problem2AllProductExceptIndex {

    public static Integer[] productAllExceptI(Integer[] input) {
        Integer[] prodUptoI = new Integer[input.length];
        Integer[] prodBeyondI = new Integer[input.length];
        Integer[] output = new Integer[input.length];

        prodUptoI[0] = 1;
        //populate prodUptoI
        for (int i = 1; i < input.length; i++) {
            prodUptoI[i] = prodUptoI[i - 1] * input[i - 1];
        }
        System.out.println("Prod upto i: " + Arrays.asList(prodUptoI));
        //Result at this point should be - [1, 1, 2, 6, 24]

        prodBeyondI[input.length - 1] = 1;
        //populate prodBeyondI
        for (int i = input.length - 2; i > -1; i--) {
            prodBeyondI[i] = prodBeyondI[i + 1] * input[i + 1];
        }
        System.out.println("Prod beyond i: " + Arrays.asList(prodBeyondI));
        //Result at this point should be - [120,60,20,5,1]

        for (int i = 0; i < input.length; i++) {
            output[i] = prodUptoI[i] * prodBeyondI[i];
        }

        //Can do in place of i also
        return output;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.asList(productAllExceptI(new Integer[] {1, 2, 3, 4, 5})));
        System.out.println("should equal");
        System.out.println(Arrays.asList(new Integer[] {120, 60, 40, 30, 24}));
    }

}
