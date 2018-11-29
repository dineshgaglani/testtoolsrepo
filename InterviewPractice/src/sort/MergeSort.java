package sort;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {
    public List<Integer> merge(List<Integer> leftPart, List<Integer> rightPart) {
        List<Integer> merged = new ArrayList<>();
        int leftPartCtr = 0;
        int rightPartCtr = 0;
        for (int i = 0; i < leftPart.size() + rightPart.size(); i++ ) {
            if (leftPartCtr >= leftPart.size()) { merged.add(rightPart.get(rightPartCtr)); rightPartCtr++; }
            else if (rightPartCtr > rightPart.size()) { merged.add(leftPart.get(leftPartCtr)); leftPartCtr++; }
            else if (leftPart.get(leftPartCtr) < rightPart.get(rightPartCtr)) { merged.add(leftPart.get(leftPartCtr)); leftPartCtr++;}
            else {merged.add(rightPart.get(rightPartCtr)); rightPartCtr++; }
        }
        return null;
    }

    public List<Integer> mergeSortOnSortedLists(List<List<Integer>> sortedLists, int start, int end) {
        if (sortedLists.size() == 1) {
            return sortedLists.get(0);
        }

        int midIndex = (start + end)/2;
        List<Integer> leftPart = mergeSortOnSortedLists(sortedLists.subList(start, midIndex), start, midIndex);
        List<Integer> rightPart = mergeSortOnSortedLists(sortedLists.subList(midIndex + 1, end), midIndex + 1, end);
        return merge(leftPart, rightPart);
    }
}
