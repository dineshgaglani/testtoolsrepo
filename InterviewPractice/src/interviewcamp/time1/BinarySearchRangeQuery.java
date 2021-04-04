package interviewcamp.time1;


import java.util.Arrays;
import java.util.List;

public class BinarySearchRangeQuery {

    public static List<Integer> getBetween(Integer[] ip, Integer low, Integer high) {
        Integer lowIndex = getLowIndex(ip, low);
        Integer highIndex = getHighIndex(ip, high);
        return Arrays.asList(ip).subList(lowIndex, highIndex);
    }

    private static Integer getHighIndex(Integer[] ip, Integer high) {
        Integer l = 0;
        Integer r = ip.length;

        while (l < r) {
            int mid = (l + r)/2;
            if (ip[mid] >= high) {
                //Will fail if mid == 0
                if (ip[mid - 1] < high || ip[mid] == high) {
                    return mid;
                }
            } else {
                l = mid;
            }
        }

        return ip.length;
    }

    public static Integer getLowIndex(Integer[] ip, Integer low) {
        Integer l = 0;
        Integer r = ip.length;

        while (l < r) {
            int mid = (l + r)/2;
            if (ip[mid] <= low) {
                // will fail if mid == length
                if(ip[mid + 1] > low || ip[mid] == low) {
                    return mid;
                }
                l = mid;
            } else {
                r = mid;
            }
        }

        return 0;
    }

    public static void main (String args[]) {
        System.out.println(getBetween(new Integer[] {3 , 8 , 10, 15, 17, 22, 45, 87}, 9, 23));
    }

}
