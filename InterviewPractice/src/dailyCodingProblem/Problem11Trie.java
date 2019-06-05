package dailyCodingProblem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
    Implement an autocomplete system. That is, given a query string s and a set of all possible query strings, return all strings in the set that have s as a prefix.

    For example, given the query string de and the set of strings [dog, deer, deal], return [deer, deal].

    Hint: Try pre-processing the dictionary into a more efficient data structure to speed up queries.
 */
public class Problem11Trie {

    class Trie {
        HashMap<Character, Trie> rootcharToTrieMap = new HashMap<>();
    }

    Trie trie = new Trie();

    public List<String> getAllWordsWithPrefix (String prefix) {

        List<String> allWordsWithPrefix = new ArrayList<>();
        Trie navigatableTrie = trie;
        for (int i = 0; i < prefix.length(); i++) {
            navigatableTrie = navigatableTrie.rootcharToTrieMap.get(prefix.charAt(i));
        }

        List<String> trieWords = getAllWordsInTrie(navigatableTrie);

        trieWords.forEach(w -> allWordsWithPrefix.add(prefix + w));

        return allWordsWithPrefix;
    }

    private List<String> getAllWordsInTrie(Trie navigatableTrie) {
        List<String> appendedWords = new ArrayList<>();
        if (navigatableTrie.rootcharToTrieMap.keySet().size() < 1) {
            appendedWords.add("");
            return appendedWords;
        }
        for (Character nextChar : navigatableTrie.rootcharToTrieMap.keySet()) {
            List<String> nextCharWords = getAllWordsInTrie(navigatableTrie.rootcharToTrieMap.get(nextChar));
            nextCharWords.forEach(w -> appendedWords.add(nextChar + w));
        }

        return  appendedWords;
    }

    /*
        Mistakes - Couldn't even THINK of this, was confused between recursion and iteration
        Was thinking about creating last object and returning with recursion taking care of the assignment to previous object

        This alternate method of creating an Empty object and assigning it a value later did not EVER occur even
     */
    public void addWordsToTrie (String word) {
        Trie navigatableTrie = this.trie;
        word = word.toLowerCase();
        for (int i = 0; i < word.length(); i++) {
            if (!navigatableTrie.rootcharToTrieMap.containsKey(word.charAt(i))) {
                navigatableTrie.rootcharToTrieMap.put(word.charAt(i), new Trie());
            }
            navigatableTrie = navigatableTrie.rootcharToTrieMap.get(word.charAt(i));
        }

    }

    public static void main (String args[]) {
        Problem11Trie pt = new Problem11Trie();
        pt.addWordsToTrie("Absurd");
        pt.addWordsToTrie("Absolute");
        pt.addWordsToTrie("absolutely");
        pt.addWordsToTrie("busy");
        pt.addWordsToTrie("asbestos");
        pt.addWordsToTrie("after");
        System.out.println(pt.getAllWordsWithPrefix("a"));
        System.out.println(pt.getAllWordsWithPrefix("ab"));
        System.out.println(pt.getAllWordsWithPrefix("af"));
        System.out.println(pt.getAllWordsWithPrefix("b"));
    }

}
