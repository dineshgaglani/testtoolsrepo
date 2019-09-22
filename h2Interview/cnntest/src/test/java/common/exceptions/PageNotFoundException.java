package common.exceptions;

/**
 * Created by dgaglani on 1/16/2018.
 */
public class PageNotFoundException extends RuntimeException {

    public PageNotFoundException(String string) {
        super(string);
    }
}
