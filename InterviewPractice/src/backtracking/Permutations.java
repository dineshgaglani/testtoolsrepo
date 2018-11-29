package backtracking;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Permutations {

//    public static void permutate (char[] strings, int currIndex) {
//        if (currIndex >= strings.length) {
//            System.out.println(strings);
//        } else {
//
//            for (int i = currIndex; i < strings.length; i++) {
//                //Choose - we choose a spot for currIndex, so swap
//                Utils.swapChars(strings, currIndex, i);
//
//                //Explore
//                permutate(strings, currIndex + 1);
//
//                //Unchoose
//                Utils.swapChars(strings, currIndex, i);
//            }
//
//        }
//
//    }

    public static void permutate(Integer[] a, int startIndex) {
        if (startIndex == a.length) {
            System.out.println(Arrays.asList(a));
            return;
        }
        for (int i = startIndex; i < a.length; i++) {
            //Choose
            sort.Utils.swap(Arrays.asList(a), startIndex, i);

            //Explore
            permutate(a, startIndex + 1);

            //Unchoose
            sort.Utils.swap(Arrays.asList(a), startIndex, i);
        }
    }

    /*
        {1, 2, 3, 4, 5}
     */
    public static void combinations(Integer a[], int numCombs, Integer[] collector, int collectorIndex, int startPos) {
        if (numCombs == 0) {
            System.out.println(Arrays.asList(collector));
        } else {
            for(int i = startPos; i < a.length; i++) {
                //Choose
                collector[collectorIndex] = a[i];

                //Explore
                combinations(a, numCombs - 1, collector, collectorIndex + 1, i);
            }
        }
    }

    public static void phoneNumberPnemonics (Integer[] phoneNum, Map<Integer, List<String>> digitStringMap, int digitIndex, String pnemonicString) {
        if (pnemonicString.length() == phoneNum.length) {
            System.out.println(pnemonicString);
            return;
        }
        else {
            List<String> charsForDigit = digitStringMap.get(digitIndex);
            if(charsForDigit != null) {
                for (int i = 0; i < charsForDigit.size(); i++) {
                    pnemonicString = pnemonicString + charsForDigit.get(i);
                    phoneNumberPnemonics(phoneNum, digitStringMap, digitIndex + 1, pnemonicString);
                    pnemonicString = pnemonicString.substring(0, pnemonicString.length() - 1);
                }
            } else {
                phoneNumberPnemonics(phoneNum, digitStringMap, digitIndex + 1, pnemonicString);
            }

        }
    }

    public static void combinationsUpto(Integer[] arr, int numCombinations) {
        for (int i = 0; i < numCombinations; i++) {
            Integer[] buffer = new Integer[numCombinations];
            printCombinations(arr, buffer, 0, 0);
        }
    }

    public static void printCombinations(Integer a[], Integer[] buffer, int bufferIndex, int arrayIndex) {
        if (bufferIndex == buffer.length) {
            System.out.println(Arrays.asList(buffer));
            return;
        }
        for (int i = arrayIndex; i < a.length; i++) {
            //Choose
            buffer[bufferIndex] = a[i];

            //Explore
            printCombinations(a, buffer, bufferIndex + 1, arrayIndex + 1);
        }
    }

    public static void main (String args[]) {
//        permutate(new char[] {'a', 'b', 'c'}, 0);
        permutate(new Integer[] {1, 2, 3}, 0);
        combinations(new Integer[] {1, 2, 3, 4, 5}, 2, new Integer[2], 0, 0);

        Map<Integer, List<String>> digitStringMap = new HashMap();
        digitStringMap.put(2, Arrays.asList(new String[] {"A", "B", "C"}));
        digitStringMap.put(3, Arrays.asList(new String[] {"D", "E", "F"}));
        digitStringMap.put(4, Arrays.asList(new String[] {"G", "H", "I"}));

        phoneNumberPnemonics(new Integer[] {2, 3, 4}, digitStringMap, 0, "");

        combinationsUpto(new Integer[] {1, 2, 3, 4, 5}, 3);
    }


}
