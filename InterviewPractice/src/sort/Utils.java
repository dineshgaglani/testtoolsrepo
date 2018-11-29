package sort;

import java.util.List;

public class Utils {

    public static void swap (List<Integer> list, int swapPos1, int swapPos2) {
        int temp = list.get(swapPos1);
        list.set(swapPos1, list.get(swapPos2));
        list.set(swapPos2, temp);
    }

}
