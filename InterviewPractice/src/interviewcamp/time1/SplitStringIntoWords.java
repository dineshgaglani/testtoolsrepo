package interviewcamp.time1;

import java.util.*;

/*Questions -

What should terminal condition be...?
Reaching end of the string can mean both that the end of the string was in the dictionary and also it wasn't
Could check after each split if the remaining string is in the dict, if not then explore further

Looking at the implementation - Why do we need to return a 'boolean', why isn't it void just as the earlier backtracking functions
Why isn't the terminal condition just check unsplit from start and return true if the string exists in the dict
 */
public class SplitStringIntoWords {

    public static List<String> getWords(String unsplit, Set<String> dict) {
        List<String> buffer = new ArrayList<>();
        getWords(unsplit, dict, buffer, 0);
        return buffer;
    }

    public static boolean getWords(String unsplit, Set<String> dict, List<String> buffer, Integer start) {
        if (start == unsplit.length()) {
            //<MISTAKE - same mistake repeated, checking from "start"  AFTER condition of start being end of string is true>
            /*if (dict.contains(unsplit.substring(start))) {
                return true;
            } else {
                return false;
            }*/
            //END OF MISTAKE
            /*
               Explanation:
               This block doesn't have return false because, when the end of the string is reached from a recursion,
                the end of the string (last word - from 'start' of the prev recursion to 'end' of string) already has been found
                and after this is called, if the last section was not a word, the prev recursion would've returned false and this woudln't have been called
             */

            //Correction
            return true;
            //End of correction
        }
        for(int i = start; i <= unsplit.length(); i++) {
            String split = unsplit.substring(start, i);
            if (dict.contains(split)) {
                buffer.add(split);
                if (getWords(unsplit, dict, buffer, i)) {
                    return true;
                } else {
                    buffer.remove(split);
                }
            }
        }
        return false;
    }

    public static void main(String args[]) {
        Set<String> dict = new HashSet<>();
        dict.addAll(Arrays.asList(new String[] {"i", "like", "mango", "mangot", "ango", "an" , "g"}));
        System.out.println(getWords("ilikemangotango", dict) + " should equal [i, like, mangot, ango]");
        System.out.println(getWords("ilikemangotangangog", dict) + " should equal [i, like, mangot, an, g, ango, g]");
    }
}
