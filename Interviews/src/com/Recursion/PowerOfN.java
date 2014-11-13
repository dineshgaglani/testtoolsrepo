package com.Recursion;

/**
 * Created by dgaglani on 5/23/14.
 */
public class PowerOfN {

    public static int findPower(int n, int exponent) {
        // need n * n(exponent times)
        if(exponent == 0) { return 1;}
        return n * findPower(n, exponent--);
    }

    public static int findPowerLogNTime(int n, int exponent) {
        //15 - 2 power 7 * 2 power 7 * 2
        //7 - 2 power 3 * 2 power 3 * 2
        //3 - 2 power 2 * 2
        //2 - 2 power 1 * 2 power 1
        //1 - 2 power 1
        if(exponent == 0) {
            return 1;
        }
        int temp = findPowerLogNTime(n, exponent/2);
        if(exponent%2 == 0) {
            return n * temp;
        }
        else {
            return n * temp * temp;
        }

    }


    public static void main(String args[]) {
        findPowerLogNTime(2, 15);
    }
}
