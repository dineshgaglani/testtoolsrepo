package com.arrays;

import java.util.Collection;

/**
 * Created by dgaglani on 6/21/14.
 */
public class Utils {

    public static <T extends Iterable> void printArray(T t) {
        for(Object elem : t) {
            System.out.print(elem + "\t");
        }
      System.out.println();
    }
}
