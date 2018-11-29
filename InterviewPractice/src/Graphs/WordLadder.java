package Graphs;


import java.util.*;

public class WordLadder {

    class WordsAndParents {
        String word;
        String parent;

        WordsAndParents(String word, String parent) {
            this.word = word;
            this.parent = parent;
        }

    }
    /*
            Word Ladder Problem: You are given 2 words A and B, both of the same length. Your task is to transform
            one word to another changing only one letter each time. Each intermediate word should be a valid word in the
            dictionary. Print out each intermediate word.
            A = CAB, B = DOG
            Result: CAB -> COB -> COG -> DOG
            dictionary {}

            Algorithm: We use bfs to solve the word ladder and every next level for a word has 1 letter different from it.
            The lowest level we encounter the target word is what we print.

            We can prove that this is the shortest distance this way:
            Lets say that there is a word that is at a shorter distance from the current level of the target, if this was
            the case the word would have already been found because we will have already scanned for that word, which means
            such a word cannot exist

            Algorithm:
            We put the first word in a queue and while the queue is not empty
            we dequeue and iterate over the word domain to scan for words that have only 1 character different from the
            currently dequeued item, we add all of these in the list until we find a word that matches the target. We also
            store a reference to the parent of every word we put inside the queue. If the queue becomes empty before we
            find the target word it means that there it cannot be reached by changing only one letter and using the domain
            provided.
     */
    public void printWordLadder (HashSet<String> wordDomain, String inputWord, String targetWord) {
        List<WordsAndParents> wordsQueue = new ArrayList<>();
        String delim = "delimiter";
        wordsQueue.add(new WordsAndParents(inputWord, ""));
        wordsQueue.add(new WordsAndParents(delim, delim));
        int level = 0;

        if (!wordDomain.contains(targetWord)) {
            System.out.println("--------");
        }

        List<WordsAndParents> resultCollector = new ArrayList<>();
        while (wordsQueue.size() > 0) {
            String sourceWord = wordsQueue.get(0).word;
            if (sourceWord.equals(delim)) {
                level++;
            }
            System.out.println(" parent: " + wordsQueue.get(0).parent + " Word: " + sourceWord);
            wordsQueue.remove(0);

            if (sourceWord.equals(delim)) {
                continue;
            }

            ArrayList<String> found = new ArrayList<>();

            for (String domainWord : wordDomain) {
                if (sourceWord.equals(targetWord)) {
                    System.out.println("Distance is : " + level);
                }
                if (getDiffCharsCount(sourceWord, domainWord) == 1) {
                    wordsQueue.add(new WordsAndParents(domainWord, sourceWord));
                    found.add(domainWord);
                }
            }
            wordDomain.removeAll(found);
            wordsQueue.add(new WordsAndParents(delim, delim));
        }

    }

    private static int getDiffCharsCount(String sourceWord, String domainWord) {
        int diffCtr = sourceWord.length();
        for (int i = 0; i < domainWord.length(); i++) {
            if (sourceWord.charAt(i) == domainWord.charAt(i)) {
                diffCtr--;
            }
        }
        return diffCtr;
    }

    /* with parent map */
    public static void wordLadderAttempt2(ArrayList<String> domain, String sourceWord, String targetWord) {
        List<String> q = new ArrayList<>();
        java.lang.String delimiter = "DELIM";
        q.add(sourceWord);
        q.add(delimiter);

        Map<String, String> parentMap = new HashMap<>();
        int level = 0;

        while (q.size() > 0) {
            sourceWord = q.get(0);
            q.remove(sourceWord);
            if (sourceWord.equals(delimiter)) {
                level++;
                continue;
            }

            List<String> found = new ArrayList<>();
            for (String domainWord : domain) {
                if (sourceWord.equals(targetWord)) {
                    printParentMap(sourceWord, parentMap);
                }
                if (getDiffCharsCount(domainWord, sourceWord) == 1) {
                    found.add(domainWord);
                    q.add(domainWord);
                    parentMap.put(domainWord, sourceWord);
                }
            }
            domain.removeAll(found);
        }
    }

    private static void printParentMap(String sourceWord, Map<String, String> parentMap) {
        String key = sourceWord;
        System.out.print(key + " --> ");
        while (key != null) {
            System.out.print(parentMap.get(key) + " --> ");
            key = parentMap.get(key);
        }
    }

    public static void main(String args[]) {

        WordLadder w = new WordLadder();
        String[] ss = new String[] { "POON", "PLEA", "SAME", "POIE", "PLIE", "PLEE", "PLEA", "POIN", "LOON", "MOON" };
        ArrayList<String> domain = new ArrayList<>();
        Arrays.stream(ss).forEach(s -> domain.add(s));
//        w.printWordLadder(domain, "TOON", "PLEA");
        WordLadder.wordLadderAttempt2(domain, "TOON", "PLEA");
    }
}
