package leetcode;

/**
 * Created by Dinesh on 9/17/2019.
 */
public class Atoi {
    public int myAtoi(String str) {
        /*1. trim the string
        2. split the string
        3. check whether the first char is a numeric value or '-'
        4. if no, return 0
        5. If yes, keep storing in an array until non-numeric char is encountered
        6. parse the array
            6a. 10^length - counter * value in the array*/

        String trimmed = str.trim();
        String[] split = trimmed.split(" ");

        String toParse = split[0];
        boolean isFirstCharInt = false;
        try {
            Integer.parseInt(toParse.charAt(0) + "");
            isFirstCharInt = true;
        } catch(Exception e) {
            //First char is not int
        }

        boolean isNeg = false;
        Integer intRet = 0;
        if (toParse.length() == 0) {return 0;}
        if(toParse.charAt(0) == 45 || isFirstCharInt || toParse.charAt(0) == 43) {
            if (toParse.charAt(0) == 45 || toParse.charAt(0) == 43) {
                if (toParse.charAt(0) == 45) {isNeg = true;}
                toParse = toParse.substring(1, toParse.length());
            }
            if (toParse.length() == 0) {return 0;}
            Double ret = Double.parseDouble(toParse);
            if(ret < Integer.MAX_VALUE) {
                intRet = ret.intValue();
            } else {
                intRet = Integer.MAX_VALUE;
                if (isNeg)
                    return Integer.MIN_VALUE;
            }
            if(isNeg) {
                return -1 * intRet;
            } else {
                return intRet;
            }
        }
        return intRet;
    }

    public static void main(String args[]) {
        Atoi atoi = new Atoi();
        System.out.println(atoi.myAtoi("    -42"));
        System.out.println(atoi.myAtoi("    3.14159"));
        System.out.println(atoi.myAtoi("   +1"));
        System.out.println(atoi.myAtoi("   +-2"));
    }
}
