package sort;

public class ScrambledSortedBinarySearch {

    //returns index
    public int findPivot(int[] arr, int startIndex, int endIndex) {
        int midIndex = (startIndex + endIndex)/2;
        if (midIndex == startIndex) {
            //Terminal condition
            return startIndex;
        }

            //Identify pivot point
            if (arr[startIndex] < arr[midIndex]) {
            //This half sorted, pivot in other half
            return findPivot(arr, midIndex, endIndex);
        } else {
            return findPivot(arr, startIndex, midIndex);
        }
    }

    public int binarySearch(int[] arr, int startIndex, int endIndex, int target) {

        int midIndex = (startIndex + endIndex)/2;
        if (startIndex > endIndex || midIndex >= arr.length || endIndex > arr.length) {
            return -1;
        }
        if (arr[midIndex] == target) {
            return midIndex;
        }

        if (arr[midIndex] > target) {
            return binarySearch(arr, startIndex, midIndex - 1, target);
        } else {
            return binarySearch(arr, midIndex + 1, endIndex, target);
        }
    }

    public static void main(String args[]) {
//        int[] arr = new int[] {  6, 7, 8, 1, 2, 3, 4, 5 };
//        ScrambledSortedBinarySearch s = new ScrambledSortedBinarySearch();
//        int pIndex = s.findPivot(arr, 0, arr.length);
//        System.out.println(pIndex);
//
//        int r1Index = s.binarySearch(arr, 0, pIndex, 6);
//        int r2Index = s.binarySearch(arr, pIndex + 1, arr.length, 6);
//        System.out.println(Math.max(r1Index, r2Index));

//        int[] arr = new int[] {  1, 2, 3, 4, 5, 6, 7, 8 };
//        ScrambledSortedBinarySearch s = new ScrambledSortedBinarySearch();
//        int pIndex = s.findPivot(arr, 0, arr.length);
//        System.out.println(pIndex);

//        int r1Index = s.binarySearch(arr, 0, pIndex, 6);
//        int r2Index = s.binarySearch(arr, pIndex + 1, arr.length, 6);
//        System.out.println(Math.max(r1Index, r2Index));

//        int r1Index = s.binarySearch(arr, 0, pIndex, 1);
//        int r2Index = s.binarySearch(arr, pIndex + 1, arr.length, 1);
//        System.out.println(Math.max(r1Index, r2Index));

//        int r1Index = s.binarySearch(arr, 0, pIndex, 8);
//        int r2Index = s.binarySearch(arr, pIndex + 1, arr.length, 8);
//        System.out.println(Math.max(r1Index, r2Index));

//        int r1Index = s.binarySearch(arr, 0, pIndex, 9);
//        int r2Index = s.binarySearch(arr, pIndex + 1, arr.length, 9);
//        System.out.println(Math.max(r1Index, r2Index));

//        int[] arr = new int[] {  1, 2, 3, 4, 5, 6, 7, 10 };
//        ScrambledSortedBinarySearch s = new ScrambledSortedBinarySearch();
//        int pIndex = s.findPivot(arr, 0, arr.length);
//        System.out.println(pIndex);
//
//        int r1Index = s.binarySearch(arr, 0, pIndex, 9);
//        int r2Index = s.binarySearch(arr, pIndex + 1, arr.length, 9);
//        System.out.println(Math.max(r1Index, r2Index));

//        int[] arr = new int[] {  5, 6, 7, 8, 1, 2, 3, 4 };
//        ScrambledSortedBinarySearch s = new ScrambledSortedBinarySearch();
//        int pIndex = s.findPivot(arr, 0, arr.length);
//        System.out.println(pIndex);

//        int r1Index = s.binarySearch(arr, 0, pIndex, 7);
//        int r2Index = s.binarySearch(arr, pIndex + 1, arr.length, 7);
//        System.out.println(Math.max(r1Index, r2Index));

//        int r1Index = s.binarySearch(arr, 0, pIndex, 3);
//        int r2Index = s.binarySearch(arr, pIndex + 1, arr.length, 3);
//        System.out.println(Math.max(r1Index, r2Index));

//        int r1Index = s.binarySearch(arr, 0, pIndex, 9);
//        int r2Index = s.binarySearch(arr, pIndex + 1, arr.length, 9);
//        System.out.println(Math.max(r1Index, r2Index));

//        int[] arr = new int[] {  5, 6, 7, 8, 1, 2, 3, 3, 4 };
//        ScrambledSortedBinarySearch s = new ScrambledSortedBinarySearch();
//        int pIndex = s.findPivot(arr, 0, arr.length);
//        System.out.println(pIndex);

//        ScrambledSortedBinarySearch s = new ScrambledSortedBinarySearch();
//        int pIndex = s.findPivot(arr, 0, arr.length);
//        System.out.println(pIndex);

//        int r1Index = s.binarySearch(arr, 0, pIndex, 7);
//        int r2Index = s.binarySearch(arr, pIndex + 1, arr.length, 7);
//        System.out.println(Math.max(r1Index, r2Index));

//        int r1Index = s.binarySearch(arr, 0, pIndex, 3);
//        int r2Index = s.binarySearch(arr, pIndex + 1, arr.length, 3);
//        System.out.println(Math.max(r1Index, r2Index));

//        int r1Index = s.binarySearch(arr, 0, pIndex, 4);
//        int r2Index = s.binarySearch(arr, pIndex + 1, arr.length, 4);
//        System.out.println(Math.max(r1Index, r2Index));

//        int r1Index = s.binarySearch(arr, 0, pIndex, 9);
//        int r2Index = s.binarySearch(arr, pIndex + 1, arr.length, 9);
//        System.out.println(Math.max(r1Index, r2Index));

//        int[] arr = new int[] {  5, 6, 6, 7, 8, 1, 2, 3, 3, 4 };
//        ScrambledSortedBinarySearch s = new ScrambledSortedBinarySearch();
//        int pIndex = s.findPivot(arr, 0, arr.length);
//        System.out.println(pIndex);

//        int r1Index = s.binarySearch(arr, 0, pIndex, 4);
//        int r2Index = s.binarySearch(arr, pIndex + 1, arr.length, 4);
//        System.out.println(Math.max(r1Index, r2Index));

//        int r1Index = s.binarySearch(arr, 0, pIndex, 7);
//        int r2Index = s.binarySearch(arr, pIndex + 1, arr.length, 7);
//        System.out.println(Math.max(r1Index, r2Index));

//        int r1Index = s.binarySearch(arr, 0, pIndex, 8);
//        int r2Index = s.binarySearch(arr, pIndex + 1, arr.length, 8);
//        System.out.println(Math.max(r1Index, r2Index));

//        int r1Index = s.binarySearch(arr, 0, pIndex, 1);
//        int r2Index = s.binarySearch(arr, pIndex + 1, arr.length, 1);
//        System.out.println(Math.max(r1Index, r2Index));

        int[] arr = new int[] { 1 };
        ScrambledSortedBinarySearch s = new ScrambledSortedBinarySearch();
        int pIndex = s.findPivot(arr, 0, arr.length);
        System.out.println(pIndex);

//        int r1Index = s.binarySearch(arr, 0, pIndex, 1);
//        int r2Index = s.binarySearch(arr, pIndex + 1, arr.length, 1);
//        System.out.println(Math.max(r1Index, r2Index));

        int r1Index = s.binarySearch(arr, 0, pIndex, 2);
        int r2Index = s.binarySearch(arr, pIndex + 1, arr.length, 2);
        System.out.println(Math.max(r1Index, r2Index));
    }

}
