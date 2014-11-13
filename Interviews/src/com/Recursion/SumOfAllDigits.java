package com.Recursion;

/**
 * Created by dgaglani on 5/23/14.
 */
public class SumOfAllDigits {

    public int calculateDigitsSum(int number) {
        //149 mod 10 = 9 and then divide by 10 = 14
        //14 mod 10 = 4 and then divide by 10 = 1
        //1 mod 10 = 1 and then divide by 10 = 0
        if(number == 0) {
            return 0;
        }
        int currDigit = number % 10;
        int currNum = number/10;
        return currDigit + calculateDigitsSum(currNum);
    }
}
