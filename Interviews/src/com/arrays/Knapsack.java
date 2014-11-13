package com.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dgaglani on 6/22/14.
 */
public class Knapsack {

    static List<Integer> selectedList = new ArrayList<Integer>();
    static int maxItemQuantity = 0;
    public static void main(String args[]) {
            int[] arr = new int[] {1, 20, 0, 4};
            System.out.println("Max possible weight: " + knapSackThis(arr, 3, 0, 0));
            System.out.println(maxItemQuantity);
    }

    public static int knapSackThis(int[] weights, int maxWeight, int currIndex, int currWeight) {
        if(currWeight > maxWeight) {
            maxItemQuantity = Math.max(maxItemQuantity, selectedList.size() - 1);
            return currWeight - weights[currIndex - 1];
        }
        if(currIndex == weights.length) {
            return currWeight;
        }
        if(selectedList.contains((Object)weights[currIndex])) {
            selectedList.remove((Object)weights[currIndex]);
        }
        int maxItemWeight = knapSackThis(weights, maxWeight, currIndex + 1, currWeight);
        selectedList.add(weights[currIndex]);
        maxItemWeight = Math.max(maxItemWeight, knapSackThis(weights, maxWeight, currIndex + 1, currWeight + weights[currIndex]));

        return maxItemWeight;
    }

}
