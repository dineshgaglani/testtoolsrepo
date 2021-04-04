package interviewcamp.time1;

import java.util.*;

public class PhoneNumberPnemonics {

    public static void printPnemonics(List<Integer> num) {
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

        String[] buffer = new String[num.size()];
        printPnemonics(num, buffer, 0, 0, numToCharMap);
    }

    public static void printPnemonics(List<Integer> num, String[] buffer, Integer filled, Integer curr, Map<Integer, List<String>> numToCharMap) {
        if (filled == buffer.length) {
            System.out.println(Arrays.asList(buffer));
            return;
        }

        for (int i = curr; i < num.size(); i++) {
            List<String> currChars = numToCharMap.get(num.get(i));
            if (currChars.size() == 0) {
                buffer[filled] = "";
                printPnemonics(num, buffer, filled + 1, i + 1, numToCharMap);
            }
            for (String currChar : currChars) {
                buffer[filled] = currChar;
                printPnemonics(num, buffer, filled + 1, i + 1, numToCharMap);
            }
        }
    }

    public static void main (String[] args) {
        printPnemonics(Arrays.asList(new Integer[] {2, 1, 3}));
    }
}
