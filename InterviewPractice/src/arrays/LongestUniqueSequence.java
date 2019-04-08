package arrays;

import java.util.HashMap;

/*
    Given a string find the length of the longest unique sequence of charaters

    We will have a start pointer and an end pointer (both starting in front) and our end game is to have the start pointer at the start
    of the longest substring and end char at the end.
    We move the end pointer until we've encountered the longest subsequence of the size we need, when we've reached that
    size, we will only have to encounter elements when we've removed one existing
    We need to keep track of the number of unique chars in a subsequence so we need a structure to hold the unique
    chars that we encountered so far, we use a map since we need to save characters and their counts as when
    we remove we should know if we should get rid of the entire character or just decrement one occurrance

    Algorithm:
    1. Move the endPointer populating the hashmap for every character
    2. When we encounter a character which if added to our map would increase its size over the desired number
    we take a difference of end - start and move the start ahead until we are rid of atleast 1 char, we then
    add the new char we encountered earlier and move end ahead until
 */
public class LongestUniqueSequence {

    public static Integer longestUniqueSubsequence (String s, Integer target) {
        HashMap<String, Integer> occurances = new HashMap<>();
        int start = 0; int end = 0;
        int max = -1;

        while (end < s.length()) {
            String currChar = s.charAt(end) + "";
            if (occurances.containsKey(currChar)) {
                occurances.put(currChar, occurances.get(currChar) + 1);
                end++;
            } else {
                if (occurances.keySet().size() == target) {
                    max = Math.max(max, end - start);
                    String charAtStart = s.charAt(start) + "";
                    if (occurances.get(charAtStart) > 1) {
                        occurances.put(charAtStart, occurances.get(charAtStart) - 1);
                    } else {
                        occurances.remove(charAtStart);
                    }
                    start++;
                } else {
                    occurances.put(currChar, 1);
                    end++;
                }

            }
        }
        max = Math.max(max, end - start);
        return max;
    }

    public static void main (String args[]) {
        System.out.println(LongestUniqueSequence.longestUniqueSubsequence("abcbbccbaaaaabbbbax", 2));
        System.out.println(LongestUniqueSequence.longestUniqueSubsequence("abcbbccbaaaaabbbba", 2));
        System.out.println(LongestUniqueSequence.longestUniqueSubsequence("abcbbca", 2));
    }
}
