package arrays;

public class SmallestPositive {

    public static int getSmallestPositive (Integer[] a) {

        for (int i = 0; i < a.length; i++) {
            while (a[i] - 1 != i && a[i] > 0 && a[i] < a.length) {
                swap(a[i] - 1, i, a);
            }
        }

        for (int i = 0; i < a.length; i++) {
            if (a[i] != i + 1) {
               return i + 1;
            }
        }

        return 0;
    }

    private static void swap(Integer index1, int index2, Integer[] a) {
        int temp = a[index1];
        a[index1] = a[index2];
        a[index2] = temp;
    }

    public static void main (String args[]) {
        System.out.println(SmallestPositive.getSmallestPositive(new Integer[] {3, 1, -1, 4} ));
    }
}


