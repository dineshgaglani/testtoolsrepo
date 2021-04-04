package interviewcamp.time2;

import java.util.HashMap;
import java.util.Map;

public class ContiguousUniqueChars {

    public static String getLongestContiguousUniqueChars(String ip) {
        int start = 0;
        int end = 0;
        int maxStart = 0;
        int maxEnd = 0;

        Map<Character, Integer> uniques = new HashMap<>();

        while (end < ip.length()) {
            Character curr = ip.charAt(end);
            if (uniques.containsKey(curr)) {
                Integer currPrevIndex = uniques.get(curr);
                while (start <= currPrevIndex) {
                    uniques.remove(ip.charAt(start));
                    start++;
                }
            }
            uniques.put(curr, end);
            end++;
            if(maxEnd - maxStart < end - start) {
                maxStart = start;
                maxEnd = end;
            }
        }

        return ip.substring(maxStart, maxEnd);
    }

    public static void main (String args[]) {
        System.out.println(getLongestContiguousUniqueChars("w") + " should equal w");
        System.out.println(getLongestContiguousUniqueChars("whatw") + " should equal what");
        System.out.println(getLongestContiguousUniqueChars("whatwhydnwhere") + " should equal atwhydn");
        System.out.println(getLongestContiguousUniqueChars("whatahywhere") + " should equal tahyw");
        System.out.println(getLongestContiguousUniqueChars("whatawhyewhere") + " should equal tawhye");
    }

}
