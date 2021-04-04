package interviewcamp.time2;

import java.util.*;

public class SplitStringIntoWords {

    public static List<String> getWords (String unsplit, Set<String> dict) {
        List<String> buffer = new ArrayList<>();
        getWords(unsplit, buffer, 0, dict);
        return buffer;
    }

    public static boolean getWords(String unsplit, List<String> buffer, Integer start, Set<String> dict) {
        if (dict.contains(unsplit.substring(start))) {
            buffer.add(unsplit.substring(start));
            return true;
        }
        for (int i = start; i < unsplit.length(); i++) {
            String split = unsplit.substring(start, i);
            if (dict.contains(split)) {
                buffer.add(split);
                if (!getWords(unsplit, buffer, i, dict)) {
                    buffer.remove(split);
                }
                //MISTAKE - MISSED THE TRUE PART
                else {
                    return true;
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
