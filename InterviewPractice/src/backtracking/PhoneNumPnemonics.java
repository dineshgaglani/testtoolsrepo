package backtracking;

import java.util.*;

/*
    Backtracking - We will need to change to exhaust all combinations of the final digit and then move to the previous
    change it and repeat all for the next before traversing back until we exhaust all possibilities.

    Pass in the digit to char map, and a collector, and a pointer to the current digit that is under change,
    if that has exceeded the phone number length, print the contents of the collector, other place in the collector
    the character we are at for the current digit (starting from 0 going upto how many ever chars are present for that
    string)
*/
public class PhoneNumPnemonics {

    public static void printPnemonics (String phoneNum, Map<String, List<String>> digitToCharMap, int currDigit, List<String> pnemonic) {
        if (currDigit == phoneNum.length()) {
            System.out.println(pnemonic);
            return;
        }
        for (String currDigitCurrChar : digitToCharMap.get(phoneNum.charAt(currDigit) + "")) {
                pnemonic.add(currDigitCurrChar);
                printPnemonics(phoneNum, digitToCharMap, currDigit + 1, pnemonic);
                pnemonic.remove(pnemonic.size() - 1);
        }

    }

    public static void main (String args[]) {
        String phoneNum = "123";
        Map<String, List<String>> digCharMap = new HashMap<>();
        digCharMap.put("1", Arrays.asList( new String[] {"A", "B", "C"} ));
        digCharMap.put("2", Arrays.asList( new String[] {"D", "E", "F"} ));
        digCharMap.put("3", Arrays.asList( new String[] {"G", "H"} ));

        printPnemonics(phoneNum, digCharMap, 0 , new ArrayList<>());
    }
}
