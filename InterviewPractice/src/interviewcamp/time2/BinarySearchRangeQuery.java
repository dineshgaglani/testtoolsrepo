package interviewcamp.time2;

import java.util.Arrays;
import java.util.List;

public class BinarySearchRangeQuery {

    public static List<Integer> getBetween(Integer[] ip, Integer low, Integer high) {
        Integer l = getLow(ip, low);
        Integer h = getHigh(ip, high);

        return Arrays.asList(ip).subList(l, h);
    }

    private static Integer getHigh(Integer[] ip, Integer high) {
        Integer l = 0;
        Integer r = ip.length;
        while (l < r) {
            int mid = (l + r)/2;

            if (ip[mid] >= high) {
                if(ip[mid - 1] < high) {
                    return mid;
                }
                if (ip[mid] == high) {
                    return mid - 1;
                }
            } else {
                l = mid;
            }
        }

        return ip.length;
    }

    private static Integer getLow(Integer[] ip, Integer low) {
        Integer l = 0;
        Integer r = ip.length;

        while(l < r) {
            Integer mid = (l + r)/2;

            if(ip[mid] <= low) {
                if(ip[mid + 1] > low) {
                    return mid;
                }
                if (ip[mid] == low) {
                    return mid + 1;
                }
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
