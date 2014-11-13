import java.util.List;

/**
 * Created by dgaglani on 4/6/14.
 */
public class Utils {

    public static void displayArray(int[] array) {
        System.out.print("Result: \t");
        for(int index = 0; index < array.length; index++) {
            System.out.print(array[index] + "\t");
        }
    }
}
