package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrintSentences {

    public static void main(String args[]) {
        String noSpSentence = "Iamasimpleman";
        List<String> words = Arrays.asList(new String[] {"I", "am", "a", "simple", "man", "new", "word"});
        String splitSentence = "I am a simple man";
        String splitSentenceActual = splitIt(noSpSentence, words);
        System.out.println(splitSentence.equals(splitSentenceActual));

        //Splitting from first does not form last word, then backtracking forms it
        noSpSentence = "abcde";
        words = Arrays.asList(new String[] {"a", "b", "c", "cde"});
        splitSentence = "a b cde";
        splitSentenceActual = splitIt(noSpSentence, words);
        System.out.println(splitSentence.equals(splitSentenceActual));

//        String s = String.format("Used %s again and %s again", "replace", "replace");
//        System.out.println(s);
    }

    private static String splitIt(String noSpSentence, List<String> words) {
        List<String> collector = new ArrayList<>();
        splitItBackTrack(noSpSentence, words, collector);
        StringBuilder resWords = new StringBuilder();
        collector.forEach(s -> resWords.append(s));
        return resWords.toString();
    }

    private static void splitItBackTrack(String noSpSentence, List<String> words, List<String> collector) {
        if (noSpSentence.length() == 0) {
            StringBuilder resWords = new StringBuilder();
            collector.forEach(s -> resWords.append(s));
            resWords.toString();
            return;
        }
        for (int i = 0; i < noSpSentence.length() + 1; i++) {
            //Choose
            String split = splitSentence(noSpSentence, i);

            //Explore if necessary
            if (isValidWord(split.split(" ")[0], words)) {
                //We pass whatever is after the space
                collector.add(split.split(" ")[0]);
                if (split.split(" ").length > 1) {
                    splitItBackTrack(split.split(" ")[1], words, collector);
                }
                //Unchoose
                if (collector.size() > 0) {
                    collector.remove(collector.size() - 1);
                }
            }

        }
    }

    private static boolean isValidWord(String s, List<String> words) {
        return words.contains(s);
    }

    private static String splitSentence(String noSpSentence, int i) {
        return noSpSentence.substring(0, i) + " " + noSpSentence.substring(i);
    }

}
