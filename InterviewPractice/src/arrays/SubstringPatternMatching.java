package arrays;

/*
    Given a String and Pattern with either '*' or '.' where * represents a series of any character and .
    represents a single any character, find if a string is same as the pattern.
    Approach: We iterate on the pattern since iterating on the string is dependent on the pattern and not
    the other way round (since when the pattern character is * we just move ahead on the main string)
    Essentially, when we encounter a * on the pattern we Stop on the pattern and move ahead on the main string,
    so the main question to answer is this: When do start moving on the pattern. The obvious answer is "when the
    next char on main string becomes the next char on pattern", the problem with that answer is that the next char
    on the main string could still count as part of the wild char (since if we have a partial match after which there
    is a mismatch - see examples below), which will result in false in some of the examples below:

    pattern: abc*de  should match abcde and also abcdde and abcfedde and abcbdefeede, it should NOT match abcddfeedf

    Cases:
    1. There are 3 cases,
        a. when we have a match, which means we need to move forward on both pattern and main string
        b. when there is a mismatch WITH a * earlier (meaning the current mismatch is still a part of the *), so we need
           to shift back to * on the pattern
        c. When there is mismatch and no * earlier, its a failure


    Algorithm:
        Iterate on the main string, if the main string matches the pattern, move ahead on both.
        If the pattern is a *, then move ahead on the main string alone until the pattern + 1th character equals the
        main string, once it does then move pattern 1 ahead
        If it's neither of these cases then check if we encountered a * earlier, if we did then this mismatch could still
        be part of the *, so backtrack to where the * was.
 */
public class SubstringPatternMatching {

    public boolean patternMatching (String pattern, String mainString) {
        int pCtr = 0 , sCtr = 0;
        int backerCtr = -1;
        while (sCtr < mainString.length()) {
            char currSChar = mainString.charAt(sCtr);
            char currPChar = pattern.charAt(pCtr);
            if (currPChar == '*') {
                backerCtr = pCtr;
                while (currSChar != pattern.charAt(pCtr + 1)) {
                    sCtr++;
                    if (sCtr == mainString.length()) {
                        break;
                    }
                    currSChar = mainString.charAt(sCtr);
                }
                //The pCtr + 1th is same as sCtr so set pCtr = pCtr + 1
                pCtr++;
                currPChar = pattern.charAt(pCtr);
            }
            if (currPChar == currSChar) {
                pCtr++;
                sCtr++;
                //If pattern has ended but main string hasn't then we still may be on the * in the pattern
                if (pCtr == pattern.length() && sCtr < mainString.length()) {
                    if (backerCtr > 0) {
                        //There was a * earlier, a mismatch means we might still be a part of *
                        pCtr = backerCtr;
                    }
                }
            } else {
                if (backerCtr > 0) {
                    //There was a * earlier, a mismatch means we might still be a part of *
                    pCtr = backerCtr;
                }
            }
        }
        //Have we reached the end of both strings
        return sCtr == mainString.length() && pCtr == pattern.length();
    }

    public boolean patternMatching2 (String pattern, String mainString) {
        int sCtr = 0; int pCtr = 0; int back = -1;
        while (sCtr < mainString.length()) {
            char sChar = mainString.charAt(sCtr);
            char pChar = pattern.charAt(pCtr);
            if (sChar == pChar) {
                sCtr++;
                pCtr++;
                if (pCtr == pattern.length() && sCtr < mainString.length()) {
                    pCtr = back;
                }
            } else if (pChar == '*') {
                back = pCtr;
                while (sChar != pattern.charAt(pCtr + 1)) {
                    sCtr++;
                    if (sCtr == mainString.length()) {
                        break;
                    }
                    sChar = mainString.charAt(sCtr);
                }
                // sChar is now equal to pChar + 1
                pCtr++;
            } else {
                //Mismatch - we are either still in * section, so go back to it
                if (back > -1) {
                    pCtr = back;
                }
            }

        }
        return sCtr == mainString.length() && pCtr == pattern.length();
    }

    public static void main(String[] args) {
       SubstringPatternMatching sp = new SubstringPatternMatching();
        System.out.println(sp.patternMatching2("abc*de", "abcde"));//true
        System.out.println(sp.patternMatching2("abc*de", "abcdde"));//true
        System.out.println(sp.patternMatching2("abc*de", "abcfedde"));//true
        System.out.println(sp.patternMatching2("abc*de", "abcbdefeede"));//true
        System.out.println(sp.patternMatching2("abc*de", "abcbfeede"));//true
        System.out.println(sp.patternMatching2("abc*de", "abcddfeedf"));//false
        System.out.println(sp.patternMatching2("abc*de", "abcfededf"));//false
    }
}
