package applyboard;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by Dinesh on 10/22/2019.
 */
public class Solution {

    public static List<String> funWithAnagrams(List<String> s) {
        // Write your code here
        // Get the chars and their counts for every word sorted ascending - call it occurrence
        // Map the above for every word with Key as the occurrence and word as the value
        // Get the first word out of every resulting entry value
        return getUnAns(s);
    }

    private static List<String> getUnAns(List<String> s) {
        List<Map<Character, Long>> ret = new ArrayList<>();
        List<String> unAns = new ArrayList<>();
        for (String is : s) {
            Map<Character, Long> characterLongMap = getCharOccrrences(is);
            if (!contains(ret, characterLongMap)) {
                ret.add(characterLongMap);
                unAns.add(is);
            }
        }
        return unAns.stream().sorted().collect(Collectors.toList());
    }

    private static boolean contains(List<Map<Character, Long>> ret, Map<Character, Long> characterLongMap) {
        for (Map<Character, Long> i : ret) {
            if (characterLongMap.equals(i)){
                return true;
            }
        }
        return false;
    }


    //Gives char to charCount Mapping
    private static Map<Character, Long> getCharOccrrences(String s) {
        HashMap<Character, Long> charMap = new HashMap();
        for (Character c : s.toCharArray()) {
            charMap.put(c, new Long(s.chars().filter(ch -> ch==c).count()));
        }

        return charMap;
    }


    public static void main (String args[]) {
        System.out.println(getUnAns(Arrays.asList(new String[] {"code", "aaagmnrs", "anagrams", "doce"})));
        System.out.println(funWithAnagrams(Arrays.asList(new String[] {"code", "doce", "ecod", "framer", "frame"})));
    }
}
