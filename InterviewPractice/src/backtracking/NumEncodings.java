package backtracking;

/*
    Given a map of num to char encoding, example 1 to a and 2 to b.... find the number of combinations that can be made
    from a given encoding

    So, if we have 1, we have a if we have 1 1 we have aa and k, which means we decoded 1 and then the next 1 and then both
    together. The backtracking algo first picks a char in the num string, identifies the number of encodings a single char
    can have and then either calls itself recursively for the next char (top down) or it calculates the value for the next char
    and adds the values for itself to the result (bottom up).

    If we go top down we need to pass in how many chars we need to decode and if we go bottom up we need to first calcualate
    for end alone and then for end - 1 and end.

    We need to call ourselves recursively 2 times because we need to call for 1 character and then for 2 characters of the
    input number.

    Backtracking Algorithm:
    Top down:
    take in a number and start index and numCharsToChoose (1 or 2), and return 1 if the substring is a valid encoding
        countEncodings(str, sIndex, numToChoose)
            int total = 0;
            if (sIndex + numToChoose > str.length) { return 0 }
            if (isValid(str.subString(sIndex, sIndex + numToChoose))) { total = 1 } // This isn't right, we can't have total increase in size for every valid char, it can only be for ALL valid char
            return total + countEncodings(str, sIndex + 1, 1) + countEncodings(str, sIndex + 2)

     The implementation above does not work because we repeat counting example: if we pass in 123, we count for 1, 2, 3
     then 1 and 23 which again incorrectly calls 23 and 2, 3. I have no clue how the program below fixes this.
     The difference here is we explore ahead with 2 characters only when we find that they are valid, but that shouldve applied
     even on 123.
     The mistake actually was incrementing the tital on seeing a valid character, it should actually return a 1 only
     after we have validated all characters are valid. So countEncodings(123) = countEncodings(1) + countEncodings(23)

    1221 - 1 2 2 1
           12 21
           12 2 1
           1 22 1
           1 2 21

21 -> 2

We cannot do memoization with this implementation because we need the index (the index of each character in the encoded
will need to act as the key for the memoization map) - Revisit this again to add memoization
 */
public class NumEncodings {

    public static int countEncodings(String encoded) {
        int total = 0;
        if (encoded.length() <= 1) { return 1; }
        total = total + countEncodings(encoded.substring(1));
        String subS = encoded.substring(0, 2);
        if (Integer.parseInt(subS) < 27) {
            total = total + countEncodings(encoded.substring(2));
        }
        return total;
    }

    public static void main (String args[]) {
        System.out.println(countEncodings("1221"));
    }

}
