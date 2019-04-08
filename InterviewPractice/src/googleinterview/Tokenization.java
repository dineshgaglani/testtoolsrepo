package googleinterview;

import java.util.ArrayList;
import java.util.Arrays;

/*
    Given a string with spaces, tokenize by space or by double quotes if present. Using library functions is allowed

    Recursive solution : Store the token in a collector, strip of the portion of delimiter stored in collector

    Where did I screw up: I was passing a boolean to decide how to tokenize, since I am stripping the string upto
    a quote and then passing again, every quote will be available in the beginning of the string.

    I also did not have startsWith case and startsWith is needed because we strip upto the quote when the string
    contains a quote.
    Takeaway: lot tougher than it looks. Had to process all cases before beginning to code
    The three cases are
    1. Does the string have a quote
    2. Does the quote need to be processed now (have we encountered it)
    3. Have we processed all quotes (or) are there any quotes to process even to begin with
 */
public class Tokenization {

    public static void tokenize (String s, ArrayList<String> collector) {
        if (s.length() == 0) {
            return;
        }
        String rem = "";
        if (s.startsWith("\"")) {
            //Strip quote
            rem = s.substring(s.indexOf("\"") + 1);
            if (rem.contains("\"")) {
                //If there is a closing quote add to collector upto close quote
                collector.add(rem.substring(0, rem.indexOf("\"")));
                //Strip off upto close quote
                rem = rem.substring(rem.indexOf("\"") + 1);
            }
        }
        else if (s.contains("\"")) {
            //Add all tokens tokenized by space upto the quotes
            collector.addAll(Arrays.asList((s.substring(0, s.indexOf("\""))).split(" ")));
            //Strip upto quotes for further processing
            rem = s.substring(s.indexOf("\""));
        } else {
            //Only spaces remain, no rem left
            collector.addAll(Arrays.asList(s.split(" ")));
        }

        tokenize(rem, collector);
    }

    public static void main (String args[]) {
        ArrayList<String> c = new ArrayList();
        tokenize("This is a string without quotes", c);
        c.clear();
        tokenize("This is a string \"with quotes\"", c);
        c.clear();
        tokenize("\"This whole is a string with quotes\"", c);
        c.clear();
        tokenize("This is a string\"with quotes and no space before it\"", c);
        c.clear();
        tokenize("This is a string \"with quotes\" and then some", c);
        c.clear();
        tokenize("This is a string \"without closing quotes", c);
    }
}
