package com.String;

/**
 * Created by dgaglani on 5/25/14.
 */
public class ReverseK {

    public static void main(String args[]) {
        //ReverseK reverseK = new ReverseK();
        //reverseK.reverseK("abcde", 2);
        System.out.print(subsetReverse(2, "abcde"));
    }

    static String subsetReverse(int K, String str) {
        String sRev = "";
        for(int i=0; i< str.length() - 1; i=i+K) {
            sRev = sRev + reverse(str.substring(i, i+K));
        }
        if(sRev.length() < str.length()) {
            sRev = sRev + str.substring(sRev.length(), str.length());
        }
        return sRev;
    }
    public static String reverse(String source){
        if(source == null || source.isEmpty()){
            return source;
        }
        String reverse = "";
        for(int i = source.length() -1; i>=0; i--){
            reverse = reverse + source.charAt(i);
        }
        return reverse;
    }


    public String reverseK(String s, int k) {
        int timesToReverse = s.length()/k;
        int startIndex = 0;
        int timesToReverseCtr = 0;
        String toReturn = s;
        int kCtr = k - 1;
        while(timesToReverseCtr < timesToReverse) {
            toReturn = reverse(toReturn, startIndex, kCtr);
            startIndex = kCtr + 1;
            kCtr = kCtr + k;
            timesToReverseCtr++;
        }
        return toReturn;
    }

    public String reverse(String s, int startIndex, int endIndex) {
        char[] sChars = s.toCharArray();
        while(startIndex < endIndex) {
            char temp = sChars[startIndex];
            sChars[startIndex] = sChars[endIndex];
            sChars[endIndex] = temp;
            startIndex++;
            endIndex--;
        }
        return new String(sChars);
    }
}
