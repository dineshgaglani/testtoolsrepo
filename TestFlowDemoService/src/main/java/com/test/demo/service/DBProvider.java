package com.test.demo.service;

/**
 * Created by dgaglani on 9/14/14.
 */
public class DBProvider {

    public String provideDb() {
        return System.getProperty("user.home") + System.getProperty("file.separator") + "devCrudFile";
    }
}
