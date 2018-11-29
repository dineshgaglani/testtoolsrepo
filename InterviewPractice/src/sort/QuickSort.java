package sort;

import java.util.List;

public class QuickSort {

    public Integer pivot (List<Integer> listToPivot) {
        //The argument is changed, but we also want the pivot position to be returned
        int pivotPos = 0;
        Integer pivot = listToPivot.get(pivotPos);
        int greaterCtr = listToPivot.size() - 1;
        int smallerCtr = 1;
        while (smallerCtr < greaterCtr) {
            while (listToPivot.get(smallerCtr) < pivot) { smallerCtr++; }
            while (listToPivot.get(greaterCtr) > pivot) { greaterCtr--; }
            Utils.swap (listToPivot, smallerCtr, greaterCtr);
        }
        Utils.swap(listToPivot, smallerCtr, pivotPos);
        return pivotPos;
    }

    public int nthBiggest(int nthPos) {
        return 0;
    }

    public void quickSort(List<Integer> listToSort, int start, int end) {
        int pivotPos = pivot(listToSort);
        quickSort(listToSort, start, pivotPos - 1);
        quickSort(listToSort, pivotPos + 1, end);
    }

}
