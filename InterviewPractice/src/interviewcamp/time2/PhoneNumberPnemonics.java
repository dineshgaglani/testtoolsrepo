package interviewcamp.time2;

import java.util.*;

public class PhoneNumberPnemonics {

    public static void printNumberPnemonics(Integer[] num) {
        Map<Integer, List<String>> numToCharMap = new HashMap<>();
        numToCharMap.put(0, new ArrayList<>());
        numToCharMap.put(1, new ArrayList<>());
        numToCharMap.put(2, Arrays.asList(new String[] {"A", "B", "C"} ));
        numToCharMap.put(3, Arrays.asList(new String[] {"D", "E", "F"} ));
        numToCharMap.put(4, Arrays.asList(new String[] {"G", "H", "I"} ));
        numToCharMap.put(5, Arrays.asList(new String[] {"J", "K", "L"} ));
        numToCharMap.put(6, Arrays.asList(new String[] {"M", "N", "O"} ));
        numToCharMap.put(7, Arrays.asList(new String[] {"P", "Q", "R", "S"} ));
        numToCharMap.put(8, Arrays.asList(new String[] {"T", "U", "V"} ));
        numToCharMap.put(9, Arrays.asList(new String[] {"W", "X", "Y", "Z"} ));

        String[] buffer = new String[num.length];
        printNumberPnemonics(num, buffer, 0, 0, numToCharMap);
    }

    public static void printNumberPnemonics(Integer[] num, String[] buffer, Integer filled, Integer start, Map<Integer, List<String>> numToCharMap) {
        if (filled == buffer.length) {
            System.out.println(Arrays.asList(buffer));
            return;
        }
        for (int i = start; i < num.length; i++) {
            Integer currNum = num[i];
            List<String> currChars = numToCharMap.get(currNum);
            if (currChars.size() == 0) {
                buffer[filled] = "";
                printNumberPnemonics(num, buffer, filled+1, i+1, numToCharMap);
            }
            for (String currChar : currChars) {
                buffer[filled] = currChar;
                printNumberPnemonics(num, buffer, filled+1, i+1, numToCharMap);
            }
        }
    }

    public static void main (String[] args) {
        printNumberPnemonics(new Integer[] {2, 1, 3});
    }
}
