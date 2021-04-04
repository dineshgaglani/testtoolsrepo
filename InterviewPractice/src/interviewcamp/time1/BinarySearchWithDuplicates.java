package interviewcamp.time1;

/*
mistake - end = arr.length - 1 will fail for 1 element and many other cases
 */

public class BinarySearchWithDuplicates {

    public static Integer search(Integer[] arr, Integer target) {
        int start = 0;
        int end = arr.length;

        while (start < end) {
            Integer mid = (start + end)/2;

            if (arr[mid] > target) {
                end = mid;
            } else if (arr[mid] < target) {
                start = mid;
            } else {
                if (mid > 0 && arr[mid - 1] == target) {
                    end = mid;
                } else {
                    return mid;
                }
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
        System.out.println(search(new Integer[] {3 , 8 , 10, 10, 10, 15, 17, 22, 45, 87, 92}, 10) + " Should equal: 2");
    }
}
