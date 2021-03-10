package amazon;

import java.util.*;

public class TopKFrequentWords {

    class ReviewStats {
        private Integer numMentions;
        private Integer numReviews;

        ReviewStats (Integer numMentions, Integer numReviews) {
            this.numMentions = numMentions;
            this.numReviews = numReviews;
        }

        public Integer getNumMentions() {
            return numMentions;
        }

        public Integer getNumReviews() {
            return numReviews;
        }

        public void setNumMentions(Integer numMentions) {
            this.numMentions = numMentions;
        }

        public void setNumReviews(Integer numReviews) {
            this.numReviews = numReviews;
        }

        @Override
        public String toString() {
            return "ReviewStats{" +
                    "numMentions=" + numMentions +
                    ", numReviews=" + numReviews +
                    '}';
        }

    }

    public List<Map.Entry<String, ReviewStats>> getTopKWords (String[] reviews, String[] games) {
        Map<String, ReviewStats> reviewStats = new HashMap<>();
        Set<String> gamesAppearedInReview = new HashSet<>();
        for (String game : games) {
            reviewStats.put(game.toLowerCase(), new ReviewStats(0, 0));
        }

        for (String review : reviews) {
            gamesAppearedInReview.clear();
            String[] reviewWords = review.split(" ");
            for (String word : reviewWords) {
                word = word.toLowerCase();
                if (reviewStats.containsKey(word)) {
                    reviewStats.get(word).setNumMentions(reviewStats.get(word).getNumMentions() + 1);
                    gamesAppearedInReview.add(word);
                }
            }
            for (String gamesInReview : gamesAppearedInReview) {
                reviewStats.get(gamesInReview).setNumReviews(reviewStats.get(gamesInReview).getNumReviews() + 1);
            }
        }

        List<Map.Entry<String, ReviewStats>> reviewStatsList = new ArrayList<>(reviewStats.entrySet());
        Collections.sort(reviewStatsList,
                Map.Entry.comparingByValue(Comparator.comparingInt(ReviewStats::getNumMentions)
                        .thenComparingInt(ReviewStats::getNumReviews)));

        return reviewStatsList;
    }


    public static void main (String args[]) {
        String rev1 = "The new Osmo is super fun for my kids";
        String rev2 = "Osmo is the hottest of the season! Osmo will be on every kid's wish list";
        String rev3 = "Expect Playmonster drone to be very popular this year! PlayMonster";
        String rev4 = "Uno is our family tradition";
        String rev5 = "Playmonster and Osmo are games to go with our family's holiday grouping, Playmonster is good";

        TopKFrequentWords tk = new TopKFrequentWords();
        tk.getTopKWords(new String [] {rev1, rev2, rev3, rev4, rev5}, new String[] {"osmo", "uno", "playmonster", "lcr"}).stream().forEach(System.out::println);
    }
}
