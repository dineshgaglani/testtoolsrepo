package common;

/**
 * Created by Dinesh on 9/22/2019.
 */
public class Utils {
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
