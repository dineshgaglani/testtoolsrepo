package arrays;

import java.util.Arrays;
import java.util.List;

/*
    Given a list of strings and a number K print a line of length K with the words in the string. The next words must be
    printed in the next line until K units of space are over, if including a word in a line exceeds K then the word must
    be moved to the next line, and the remaining words should be spaced evenly in a way that K units of space are occupied

    Approach:
    We will need to count every word length + 1 (for space) until the total count exceeds K, we will need to know how many
    words we had until we exceeded K and also how many total chars we had upto the last word until we exceeded K.
    The number of extra spaces will be proportional to number of words we have and the residue from the total word count.
    If there is as much residue as number of words then 1 extra space has to added after every word.
    Formula: diff = K - totalWordLengthForLine (this is the total extra spaces)
    diff/totalWordsForLine, means if there are 18 spaces left for the line, and the number of words in the line is 9, then
    each word needs 1 *extra* space after it. Tip: To remember what comes in the numerator, see what needs to be greater.
    So, in sum we need, numWords, numCharsOfAllWords

    Algorithm:
    Set totalCharsOfLine to 0, totalWordsOfLine to 0, startPoint to 0, endPoint to 0
    Loop over all words
       Per word add chars + 1 to totalCharsOfLine
       Increment totalWordsOfLine by 1
       Check if we exceed K, if so
          Set endPoint to totalWordsOfLine
          We need to print the earlier words now, calculate numSpaces using (K - totalCharsOfLine)/(totalWordsOfLine)
          and firstSpaces as (K - totalCharsOfLine)%(totalWordsOfLine)
          print as many spaces as firstSpaces
          Run a loop from startPoint to endPoint with i as ctr, print the word at i + space and then numSpaces
          After the loop set startPoint to endPoint
          Set totalCharsOfLine to 0, totalWordsOfLine to 0
       else continue

       Some details to remember are since there are many loops in the program, remember to create new variables in case
       some variable shouldn't be altered in a loop, example here numSpaces will be used after every words so don't
       decrement the numSpaces on each word, create a new variable numSpacesCtr instead

       Mistake made: Was iterating the loop on both if and else, and when the first "the" was printed in the test case,
       the "brown" which was counted on the else loop earlier was not being counted because we reset the totalCharsOnLine
       was being set to 0 after "the" is printed, fixed this by creating a counter and increment only on else, which meant
       that when "the" is printed, and totalChars for "brown" become 0, we again check for "brown" in the if section

       Takeaway: Will have to be careful on when to move to new words (when to increment)
 */
public class StringJustification {

    public void printJustified (List<String> s, int k) {
        int totalCharsOfLine = 0, totalWordsOfLine = 0, startPoint = 0, endPoint = 0;
        int ctr = 0;
        while (ctr < s.size()) {
            if (totalCharsOfLine + s.get(ctr).length() > k) {
                //Print the line and reset count vars
                int numSpaces = (k - totalCharsOfLine)/totalWordsOfLine;
                int prefixSpaces = (k - totalCharsOfLine)%totalWordsOfLine;
                while (prefixSpaces > 0) {
                    System.out.print(" ");
                    prefixSpaces--;
                }
                for (int i = startPoint; i < endPoint; i++) {
                    System.out.print(s.get(i) + " ");
                    int numSpaceCtr = numSpaces;
                    while (numSpaceCtr > 0) {
                        System.out.print(" ");
                        numSpaceCtr--;
                    }
                }
                System.out.print("|");
                System.out.println();//for next line
                startPoint = endPoint;
                totalCharsOfLine = 0;
                totalWordsOfLine = 0;
            } else {
                totalCharsOfLine = totalCharsOfLine + s.get(ctr).length() + 1;
                totalWordsOfLine++;
                endPoint++;
                ctr++;
            }

        }
    }
    
    public static void main(String args[]) {
        StringJustification sj = new StringJustification();
        sj.printJustified(Arrays.asList(new String[] {"the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"}), 16);
    }
}
