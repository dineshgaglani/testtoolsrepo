package dailyCodingProblem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    Given the mapping a = 1, b = 2, ... z = 26, and an encoded message, count the number of ways it can be decoded.

    For example, the message '111' would give 3, since it could be decoded as 'aaa', 'ka', and 'ak'.

    You can assume that the messages are decodable. For example, '001' is not allowed.

    Approach: Choose -> Explore -> unchoose
    We will iterate over the input string and pass in the begin index in the recursion, the recursion then chooses the
    character at index and calls recursion on the next char, the terminal condition returns, when the return happens,
    the caller then sets the corresponding character for the character at it's index and returns to its caller, which
    appends its own corresponding char to what was returned to it.

    Recursive call items needed: start index which needs to be processed,
                                 end index which needs to be processed,
                                 buffer which the function needs to fill and append its own processed value to,
                                 memo map which for a given start and end index have the value which was processed earlier

 */
public class Problem7DecodeString {

    private static List<String> decode(String s) {
        Map<String, List<String>> memo = new HashMap();
        List<String> res = decode(s, 0, 1, memo);

        return res;
    }

    /*Mistakes made - subString indexes
                    - terminal condition returns empty array and I am iterating on empty array
                    - Trimming off input string and also providing relative start and end chars, only one of the
                    2 is required
                    - Did not handle case of single character being passed
     */

    private static List<String> decode(String s, int startIndex, int endIndex, Map<String, List<String>> memo) {

        if (s.length() == 0) {
            List<String> empty = new ArrayList<>();
            empty.add("");
            return empty;
        }

        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        List<String> currRes = new ArrayList<>();
        //Choose
        String charAtIndex = s.substring(startIndex, endIndex);
        String decodedPart = valueOf(charAtIndex);

        //Explore
        List<String> buffer = decode(s.substring(startIndex + 1), 0, 1, memo);

        //Process and unchoose
        for (String bufferElem : buffer) {
            currRes.add(decodedPart + bufferElem);
        }

        if (s.length() > 1) {
            //Choose
            String charAtIndexAndNextIndex = s.substring(startIndex, startIndex + 2);
            String decodedPart2 = valueOf(charAtIndexAndNextIndex);

            //Explore
            buffer = decode(s.substring(startIndex + 2), 0, 2, memo);

            //Process and unchoose
            for (String bufferElem : buffer) {
                currRes.add(decodedPart2 + bufferElem);
            }
        }

        memo.put(s, currRes);
        return currRes;
    }

    public static List<String> decodeSimplified (String numericS, HashMap<String, List<String>> memo) {
        List<String> res = new ArrayList<>();
        if (numericS.trim().length() < 1) {
            res.add("");
            return res;
        }

        if (memo.containsKey(numericS)) {
            return memo.get(numericS);
        }

        String currPartNumericS = numericS.substring(0, 1);
        String thisDecoded = valueOf(currPartNumericS);
        List<String> decodeds1 = decodeSimplified(numericS.substring(1), memo);

        for (String decoded : decodeds1) {
            res.add(thisDecoded + decoded);
        }

        if (numericS.length() > 1) {
            String currPartNumericS2 = numericS.substring(0, 2);
            String thisDecoded2 = valueOf(currPartNumericS2);
            List<String> decodeds2 = decodeSimplified(numericS.substring(2), memo);

            for (String decoded : decodeds2) {
                res.add(thisDecoded2 + decoded);
            }
        }

        memo.put(numericS, res);

        return  res;
    }

    public static String valueOf(String numericString) {
        Integer aVal = ('a' - 1);
        return Character.toString(Integer.parseInt(numericString) + aVal);
    }


    public static void main (String args[]) {

        List<String> res = decode("11");
        System.out.println("Res contents: " + res + " should equal [aa, k]");

        res = decode("111");
        System.out.println("Res contents: " + res + " should equal [aaa, ak, ka]");

        res = decode("1111");
        System.out.println("Res contents: " + res + " should equal [aaaa, aak, aka, kaa, kk]");

        List<String> res2 = decodeSimplified("11", new HashMap<>());
        System.out.println("Res2 contents: " + res2 + " should equal [aa, k]");

        res2 = decodeSimplified("111", new HashMap<>());
        System.out.println("Res2 contents: " + res2 + " should equal [aaa, ak, ka]");

        res2 = decodeSimplified("1111", new HashMap<>());
        System.out.println("Res2 contents: " + res2 + " should equal [aaaa, aak, aka, kaa, kk]");
    }


}
