package interviewcamp.time2;

public class BinarySearch {

    public static Integer search(Integer[] arr, Integer target) {
        int start = 0;
        int end = arr.length;

        while (start < end) {
            int mid = (start + end)/2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        return -1;
    }

    public static void main (String args[]) {
        System.out.println(search(new Integer[] {3}, 3) + " Should equal: 0");
        System.out.println(search(new Integer[] {1, 2}, 1) + " Should equal: 0");
        System.out.println(search(new Integer[] {1, 2}, 2) + " Should equal: 1");
        System.out.println(search(new Integer[] {3 , 8 , 10, 15, 17, 22, 45, 87}, 10) + " Should equal: 2");
        System.out.println(search(new Integer[] {3 , 8 , 10, 15, 17, 22, 45, 87, 92}, 45) + " Should equal: 6");
        System.out.println(search(new Integer[] {3 , 8 , 10, 15, 17, 22, 45, 87, 92}, 92) + " Should equal: 8");
    }
}
