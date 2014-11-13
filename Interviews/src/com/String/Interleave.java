package com.String;

/**
 * Created by dgaglani on 5/28/14.
 */
public class Interleave {

    public void performInterleave(String s1, String s2, String interleaved, int index) {
        if(index == s1.length() + s2.length()) {
            System.out.print(interleaved);
            return;
        }
        if(index < s1.length()) {
            interleaved = interleaved + s1.charAt(index);
            performInterleave(s1, s2, interleaved, index + 1);
        }
        if(s1.length() - index < s2.length()) {
            interleaved = interleaved + s2.charAt(index - s1.length());
            performInterleave(s1, s2, interleaved, index + 1);
        }
    }

    public static void main(String args[]) {
        Interleave interLeave = new Interleave();
        interLeave.performInterleave("AB", "CD", "", 0);
    }
}
