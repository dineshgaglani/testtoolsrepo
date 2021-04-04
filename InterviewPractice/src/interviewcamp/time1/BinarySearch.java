package interviewcamp.time1;

/*
 3 target: 3
 1 2 target: 1
 1 2 target: 2
 3 , 8 , 10, 15, 17, 22, 45, 87, target: 10
 3 , 8 , 10, 15, 17, 22, 45, 87, 92 target: 45

 Questions:
 1. Which should we check - start or end
 */
public class BinarySearch {

    public static Integer search(Integer arr[], Integer target) {
        Integer start = 0;
        Integer end = arr.length - 1;
        while (start <= end) {
            if (arr[start] == target) {
                return start;
            }
            if (arr[end] == target) {
                return end;
            }
            Integer mid = (start + end)/2;
            if (arr[mid] > target) {
                end = mid;
            } else  if (arr[mid] < target) {
                start = mid;
            } else {
                return mid;
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
