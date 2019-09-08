package dailyCodingProblem;

/**
 * Created by Dinesh on 9/7/2019.
 *
 * Given a string s and an integer k, break up the string into multiple texts such that each text has a length of k or less. You must break it up so that words don't break across lines. If there's no way to break the text up, then return null.

 You can assume that there are no spaces at the ends of the string and that there is exactly one space between each word.

 For example, given the string "the quick brown fox jumps over the lazy dog" and k = 10, you should return: ["the quick", "brown fox", "jumps over", "the lazy", "dog"]. No string in the list has a length of more than 10.
 */
public class Problem57SplitLines {

    public static void splitLines(String input, int k) {
        //If there is a word bigger than k, return null
        Integer kCtr = 0;
        Integer lastSpaceCtr = 0;
        Integer lastLineEndCtr = 0;
        for (int i = 0; i < input.length(); i++) {
            kCtr++;
            if (input.charAt(i) == ' ') {
                lastSpaceCtr = i;
            }
            if (kCtr == k) {
                System.out.println(input.substring(lastLineEndCtr, lastSpaceCtr));
                lastLineEndCtr = lastSpaceCtr;
                i = lastLineEndCtr;
                kCtr = 0;
            }
        }
        System.out.println(input.substring(lastLineEndCtr, input.length()));
    }

    public static void main (String args[]) {
        splitLines("the quick brown fox jumps over the lazy dog", 10);
    }
}
