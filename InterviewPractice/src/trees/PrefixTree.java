package trees;

import java.util.*;

/*
    Given a prefix, this class should return all words that have the input as prefix,

   We use a structure called trie, a trie represents every character in a word and its respective following character which
   itself is followed by its subsequent character. A trie branches out when a particular character will be followed by
   2 characters (or the same prefix leads to 2 separate words)

   The structure needs a character as identifier (key) and then another object of itself as the value, this another
   object again has the next char as the key and the next set of possible chars as values.

   Why we follow this structure is because intuitively we know that a char can lead to more than 1 char as
   the first value in the sub-sequence, and those subsequent chars can lead to more than 1 other sub-sequence.
   So, we need a list of chars that become sub-sequences, we also need to traverse the subsequence from that subsequence
   so we need a reference to the same kind of structure

 */
public class PrefixTree {

    public class Trie {
        Map<Character, Trie> charToTrieMap = new HashMap<>();
    }

    Trie trie = new Trie();

    public void insert(String word) {
        Trie currTrie = this.trie;
        word = word.toLowerCase();
        for (int i = 0; i < word.length(); i++) {
            Character currChar = word.charAt(i);
            if (!currTrie.charToTrieMap.containsKey(currChar)) {
                currTrie.charToTrieMap.put(currChar, new Trie());
            }
            currTrie = currTrie.charToTrieMap.get(currChar);
        }
    }

    public List<String> getAllWordsWithPrefixes(String prefix) {
        prefix = prefix.toLowerCase();
        Trie currTrie = this.trie;
        for (int i = 0; i < prefix.length(); i++) {
            Character currChar = prefix.charAt(i);
            if (currTrie.charToTrieMap.containsKey(currChar)) {
                currTrie = currTrie.charToTrieMap.get(currChar);
            } else {
                return null;
            }
        }
        List<String> allWordsWithPrefix = getAllWords(currTrie);
        List<String> res = new ArrayList<>();
        for (String s : allWordsWithPrefix) {
            res.add(prefix + s);
        }
        return res;
    }

    private List<String> getAllWords(Trie currTrie) {
        List<String> allWords = new ArrayList<>();
        if (currTrie.charToTrieMap.size() == 0) {
            allWords.add("");
            return allWords;
        }
        for (Character currChar : currTrie.charToTrieMap.keySet()) {
            List<String> allWordsWithCurrCharPrefix = getAllWords(currTrie.charToTrieMap.get(currChar));
            for (String s : allWordsWithCurrCharPrefix) {
                allWords.add(currChar + s);
            }
        }
        return allWords;
    }


    public static void main (String args[]) {
        PrefixTree pt = new PrefixTree();
        pt.insert("Absurd");
        pt.insert("Absolute");
        pt.insert("absolutely");
        pt.insert("busy");
        pt.insert("asbestos");
        System.out.println(pt.getAllWordsWithPrefixes("a"));
        System.out.println(pt.getAllWordsWithPrefixes("ab"));
    }


}
