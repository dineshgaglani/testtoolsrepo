package arrays;

public class BinarySearch {

    public static int getClosestIndex (int a[], int target) {
        int start = 0;
        int end = a.length;
        while (start < end) {
            int mid = start + ((end - start)/2);
            if (a[mid] < target) {
                start = mid + 1;
            } else if (a[mid] > target) {
                end = mid - 1;
            } else {
                return mid;
            }
        }
        return Math.min(Math.abs(target - a[start + 1]), Math.abs(a[start] - target)) == Math.abs(target - a[start + 1]) ? start + 1 : start;
    }

    public static int sqRoot(int target) {
        if (target == 1) {
            return 1;
        } if (target <= 4) {
            return 2;
        }
        if (target <=9) {
            return 3;
        }
        int start = 2, end = 4;
        int endSq = end * end;
        while (endSq != target) {
            if (endSq < target) {
                start = end;
                end = 2 * end;
            } else if (endSq > target) {
                end = (start + end)/2;
            } if (end - start <= 1) {
                return end;
            }
            endSq = end * end;
        }
        return end;
    }

    public static void stringTest(String  s) {
        s = "abcd";
        System.out.println(s);
    }

    public static void main (String args[]) {
        int[] input = new int[] {10, 20, 30, 40, 50, 60};
        int closestIndex = getClosestIndex(input, 21);
        System.out.println(input[closestIndex]);

        System.out.println(sqRoot(50));

        String s = "abc";
        System.out.println(s);
        stringTest(s);
        System.out.println(s);
    }
}
