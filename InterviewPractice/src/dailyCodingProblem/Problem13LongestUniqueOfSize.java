package dailyCodingProblem;


import java.util.HashMap;

/*
Given an integer k and a string s, find the length of the longest substring that contains at most k distinct characters.

For example, given s = "abcba" and k = 2, the longest substring with k distinct characters is "bcb".
 */
public class Problem13LongestUniqueOfSize {


    private static Integer longestUniqueOfSize(String input, int uniqunessSize) {
        int leader = 0;
        int lagger = 0;
        int max = -1;

        HashMap<Character, Integer> charCount = new HashMap<>();

        while (leader < input.length()) {
            if (charCount.keySet().size() == uniqunessSize) {
                Character laggerChar = input.charAt(lagger);
                if (charCount.get(laggerChar) == 1) {
                    charCount.remove(laggerChar);
                } else {
                    charCount.put(laggerChar, charCount.get(laggerChar) - 1);
                }
                max = Math.max(max, (leader - lagger) + 1);
                lagger++;
            } else {
                addCharToCharCount(input.charAt(leader), charCount);
                leader++;
            }
        }
        max = Math.max(max, (leader - lagger) + 1);
        return max;
    }

    private static void addCharToCharCount(Character leaderChar, HashMap<Character, Integer> charCount) {
        if (charCount.containsKey(leaderChar)) {
            charCount.put(leaderChar, charCount.get(leaderChar) + 1);
        } else {
            charCount.put(leaderChar, 1);
        }
    }

    public static void main (String args[]) {

        System.out.println(longestUniqueOfSize("abcba", 2) + " should equal 3 ");
        System.out.println(longestUniqueOfSize("abcba", 3) + " should equal 5 ");

    }

}
