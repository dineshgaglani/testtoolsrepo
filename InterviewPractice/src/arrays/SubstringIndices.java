package arrays;

import java.util.*;

/*
    Given a string haystack = "aaanbbbbbccddeerrrttgjbbkpoppomfnubiuaaaddbbb" find the needle = "bbb"
    Approach - straight forward - a pointer on haystack incrementing until we see the first char on needle,
    then move both together (with a separate pointer on haystack because otherwise there will be an issue with repeated
    continuous chars in haystack and needle)
    average complexity - size of haystack
    worst complexity - size of haystack * size of needle

    Approach 2 - Unintuitive, put all of the needle's chars and their indices in a key value map, start with the
    (length - 1)th index in haystack and if needle doesn't have the element at that index, jump length - 1 indexes.
    If the element is present in needle for every index of the element in needle fan out on both sides to see if the
    rest of the chars match

    Reasoning:
    1. We choose to jump length - 1 on haystack because if we don't see any of the element of the needle in
    needleLength of the haystack, then we can be sure that this section of haystack WILL NOT match with the needle
    because if it were to match then this character WOULD HAVE TO BE in the needle (at any position). Proof by example:
    If needle were to begin a
    Example: if needle has 3 character and if it were to be found (starting/Middling/Ending) at the 3rd position on haystack
    then the 3rd position on haystack should be an element in needle. (Internalize this!!)

    pseudo code:
        1. Get all the chars and their respective indices in a hashmap - Map<String, List<Integer>> needleCharsToIndices
        2. Iterate on the haystack jump length needle and check that the character at the current index is present in needleCharsToIndices
        3. If present, iterate all indices of that character on needleCharsToIndices and place all matching indexes in matchedIndexesList
        4. Back track to start of currentIndexInHaystack - indexAtNeedle call it haystackIndex(This gives us the point where the needleSubString will have begun in haystack)
        5. Iterate on needle (call it "i" indexed at 0) and haystack (indexed at haystackIndex (FromLine4) + i) comparing chars and break on mismatch
        6. If no mismatch then record index in matchedIndexesList
 */
public class SubstringIndices {

    public static void main (String args[]) {

//        String haystack = "aaanbbbbbccddeerrrttgjbbkpoppomfnubiuaaadd";
        String haystack2 = "aaanbbbbbccddeerrrttgjbbkpoppomfnubiuaaaddbbb";
        String needle = "bbb";
        int[] expected = {5, 4, 6, 44, 43, 42};
        Integer[] actual = findSubstringIndices(haystack2, needle);

        for (int i = 0; i < expected.length; i++) {
            System.out.println(expected[i] == actual[i]);
        }

    }

    private static Integer[] findSubstringIndices(String haystack, String needle) {
        Map<String,List<Integer>> needleChars = getNeedleChars(needle);
        List<Integer> indices = new ArrayList<>();
        for (int i = needle.length() - 1; i < haystack.length(); i+= needle.length()) {
            if (needleChars.containsKey(haystack.charAt(i) + "")) {
                indices.addAll(fanOutAllOfNeedleAndHaystack(needleChars.get(haystack.charAt(i) + ""), haystack, needle, i));
            }
        }
        return indices.toArray( new Integer[] {} );
    }

    private static List<Integer> fanOutAllOfNeedleAndHaystack(List<Integer> matchedCharIndices, String haystack, String needle, int indexOnHaystack) {
        List<Integer> allFullyMathcedIndices = new ArrayList<>();
        for (Integer matchedCharIndexOnNeedle : matchedCharIndices) {
            //Look from start
            int haystackStart = indexOnHaystack - matchedCharIndexOnNeedle;
            boolean fullyMatched = true;
            for (int i = 0; i < needle.length(); i++) {
                if (i + haystackStart < haystack.length() && haystack.charAt(i + haystackStart) != needle.charAt(i)) {
                    fullyMatched = false;
                    break;
                }
            }
            if (fullyMatched) {
                allFullyMathcedIndices.add(indexOnHaystack - matchedCharIndexOnNeedle);
            }
        }
        return allFullyMathcedIndices;
    }

    private static Map<String, List<Integer>> getNeedleChars(String needle) {
        Map<String, List<Integer>> indicesMap = new HashMap<>();
        for (int i = 0; i < needle.length(); i++) {
            if (indicesMap.containsKey(needle.charAt(i) + "")) {
                indicesMap.get(needle.charAt(i) + "").add(i);
            } else {
                List<Integer> indicesOfChar = new ArrayList<>();
                indicesOfChar.add(i);
                indicesMap.put(needle.charAt(i) + "", indicesOfChar);
            }
        }
        return indicesMap;
    }
}
