package interviewcamp.time1;

import java.util.HashMap;
import java.util.Map;

/*
Given a String, find the longest substring with unique characters.

For example: "whatwhywhere" --> "atwhy"
"whatahywhere" --> "ywher"
"whatawhyewhere" --> "tawhye"
"w" --> "w"
"whatw" --> "hatw"

New approach :
Have a map that only holds unique chars, have a start and end that only point to unique chars range, have a maxStart and maxEnd
that only points to longest unique char range

//return is after maxStart but and includes i

Old Approach (wrong approach) - Had a map that held all chars and was returning all chars between repeating chars.
This is not correct
 */


public class ContiguousUniqueChars {

    /* <WRONG CODE> this gets all chars between repeating characters, its wrong because we'll include one of the repeating
                    chars in the result and all uniques after that
    public static String getLongestContiguousUniqueChars(String ip) {
        int longest = 0;
        int start = 0;
        Map<Character, Integer> occurrance = new HashMap<>();

        for (int i = 0; i < ip.length(); i++) {
            Character curr = ip.charAt(i);
            if (occurrance.containsKey(curr)) {
                if (i - occurrance.get(curr) >= longest) {
                    longest = i - occurrance.get(curr);
                    start = occurrance.get(curr) + 1;
                }
            }
            occurrance.put(curr, i);
        }

        return ip.substring(start, start + longest);

    }
     */

    private static String getLongestContiguousUniqueChars(String ip) {
        Integer start = 0;
        Integer end = 0;
        Integer maxStart = 0;
        Integer maxEnd = 0;

        Map<Character, Integer> occurranceMap = new HashMap<>();

//        "whatwhywhere" --> "atwhy"
//        "whatahywhere" --> "ywher"
//        "whatawhyewhere" --> "tawhye"
//        "w" --> "w"
//        "whatwh" --> "hatw"
        while (end < ip.length()) {
            Character curr = ip.charAt(end);
            if(end - start > maxEnd - maxStart) {
                maxStart = start;
                maxEnd = end;
            }
            if(occurranceMap.containsKey(curr)) {
                Integer lastIndexOfCurr = occurranceMap.get(curr);
                while (start <= lastIndexOfCurr) {
                    Character toRemove = ip.charAt(start);
                    occurranceMap.remove(toRemove);
                    start++;
                }
            }
            occurranceMap.put(curr, end);
            end++;
        }

        if(end - start > maxEnd - maxStart) {
            maxStart = start;
            maxEnd = end;
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
