package com.hindu.ui.tests.com.hindu.ui.tests.com.hindu.ui.tests.helpers;

/**
 * Created by dgaglani on 11/3/14.
 */
public class StringHelpers {

    private static final String PLACEHOLDER = "$";

    public static String replacePlaceholderWithString(String stringToReplacePlaceHoldersOn, String stringToReplacePlaceHoldersWith) {
        return stringToReplacePlaceHoldersOn.replaceAll(PLACEHOLDER, stringToReplacePlaceHoldersWith);
    }
}
