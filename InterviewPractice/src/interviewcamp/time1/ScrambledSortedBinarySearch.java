package interviewcamp.time1;

public class ScrambledSortedBinarySearch {

    public static Integer searchLowest(Integer[] ip) {
        int start = 0;
        int end = ip.length - 1;

        while (start < end) {
            int mid = (start + end)/2;
            /*<MISTAKE> mistake because this condition isn't possible to be true, since if it was, then above check would've failed and we would've exited the loop
            if (start == end) {
                return end + 1 + 1 ;//End + 1 since the array is 0 indexed, another +1 since we need to find lowest and 'end' gives highest
            }*/
            if (ip[start] < ip[mid]) {
                start = mid;
            }  else {
                end = mid;
            }
            if (start == end) {
                return end + 1 + 1 ;//End + 1 since the array is 0 indexed, another +1 since we need to find lowest and 'end' gives highest
            }
        }
        //size is 1 since start is not less than end (means its equal)
        return 1;
    }

    public static void main (String args[]) {
        System.out.println(searchLowest(new Integer[] {5, 6, 7, 8, 1, 2, 3}) + " should equal: 5");
        System.out.println(searchLowest(new Integer[] { 5, 6, 7, 8, 9, 10, 1}) + " should equal: 7");
        System.out.println(searchLowest(new Integer[] { 1, 2, 3, 4, 5, 6}) + " should equal: 6");
        System.out.println(searchLowest(new Integer[] {1}) + " should equal: 1");
        System.out.println(searchLowest(new Integer[] {2, 1}) + " should equal: 2");
    }
}
