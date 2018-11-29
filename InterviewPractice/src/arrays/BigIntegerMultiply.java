package arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class BigIntegerMultiply {

    public static void addOld(Integer[] a, Integer[] b) {
        List<Integer> res = new ArrayList<>();

        if (a.length < b.length) {
            Integer[] temp = a;
            a = b;
            b = temp;
        }

        //Fill B with 0s
        Integer[] newB = new Integer[a.length];
        int bCtr = 0;
        for (int i = 0; i <= a.length - b.length; i++) {
            newB[i] = 0;
            bCtr = i;
        }
        for (int i = 0; i < b.length; i++) {
            newB[bCtr++] = b[i];
        }

        int aCtr = a.length - 1;
        int carry = 0;
        while (aCtr >= 0) {
            int digitSum = a[aCtr] + newB[aCtr] + carry;

            if (digitSum > 9) {
                digitSum = (digitSum % 10);
                carry = 1;
            } else {
                carry = 0;
            }
            aCtr--;
            res.add(digitSum);
        }
        if(carry == 1) {res.add(1);}
        System.out.println(res);
    }

    /*
        To add 2 numbers we need to take care of carry which will repeatedly feature in the sum,
        so we set that to 0 intially and add it regardless of it is set to 1 or not
        The other thing is we need to set both the numbers to the same size so we can navigate from the  back
        (unit's digit) and go left and checking for nulls will be tedious

        Algo:
        1. Get the number with lesser digits, make it same as number with higher digits by appending 0s in front
            a. For this create a new array of size bigger array get the size difference (bigLen - smallLen) and fill
            these many spots on the new array with 0 and the remaining spots on the new array with values of smaller
            array
        2. Initialize carry to 0 and a resList (since the number of digits can be bigger than both inputs)
        3. Now start from the array size going leftward and adding elements in big array and new array and carry and putting unit
        digit (res%10) of result in resList (we'll have to reverse this later) and if there is a carry (res/10 > 0)
        then set carry to 1
        4. After the addition if carry is 1, add that again to the resArray (999 + 1 case)
     */
    public static List<Integer> add(Integer[] num1, Integer num2[]) {
        swapBiggerToNum1(num1, num2);
        Integer[] newArr = getSameSizeArr(num1, num2);
        int carry = 0;
        List<Integer> resArray = new ArrayList<>();
        for (int i = newArr.length - 1; i >= 0; i--) {
            int sum = num1[i] + newArr[i] + carry;
            if (sum/10 > 0) {
                carry = 1;
            }
            resArray.add(sum%10);
        }
        if (carry != 0) {
            resArray.add(carry);
        }
        Collections.reverse(resArray);
        System.out.println(resArray);
        return resArray;
    }

    private static Integer[] getSameSizeArr(Integer[] num1, Integer[] num2) {
        Integer[] newArr = new Integer[num1.length];
        int diff = num1.length - num2.length;
        for (int i = 0; i < diff; i++) {
            newArr[i] = 0;
        }
        //Mistake: did not have num2ctr
        //Takeaway, the indexes of 2 different sized elements will need different counters
        int num2ctr = 0;
        for (int i = diff; i < num1.length; i++) {
            newArr[i] = num2[num2ctr++];
        }
        return newArr;
    }

    private static void swapBiggerToNum1(Integer[] num1, Integer[] num2) {
        if (num1.length < num2.length) {
            Integer[] temp = num1;
            num1 = num2;
            num2 = temp;
        }
    }

    /*
        Simple usual multiply of 2 multidigit nums
        We initialize alliProducts
        The upper number loop starts at num1Length to 0 (i)
            We initialize a currentiDigitProduct collector
            We add as many 0s as the value of (i to length) in currentiDigitProduct.
            We initialize a carry and set it to 0
            The lower number loop starts at num2Length to 0 (j)
                we multiple j with i and add with carry, j. put product%10 in currentiDigitProduct set carry to product/10.
            We then add carry as last digit to currentiDigitProduct
            We reverse the currentiDigitProduct
            We push this to allIProducts
            We call sum on allIProducts in any order
     */
    public static void multiply(Integer[] num1, Integer[] num2) {
        List<List<Integer>> allIProducts = new ArrayList<>();
        for (int i = num1.length - 1; i >= 0; i--) {
            List<Integer> currIdigitProduct = new ArrayList<>();
            for (int zeros = i; zeros < num1.length - 1; zeros++) {
                currIdigitProduct.add(0);
            }
            int carry = 0;
            for (int j = num2.length - 1; j >= 0; j--) {
                int currProduct = num1[i] * num2[j] + carry;
                carry = currProduct/10;
                currIdigitProduct.add(currProduct%10);
            }

            currIdigitProduct.add(carry);
            Collections.reverse(currIdigitProduct);
            allIProducts.add(currIdigitProduct);
        }
        System.out.println(allIProducts);
    }

    public static void main(String args[]) {

        add(new Integer[]{9, 9, 9}, new Integer[]{1});
        multiply(new Integer[]{9, 9, 9}, new Integer[]{2});
    }
}
