package com.hindu.ui.tests.com.hindu.ui.tests.com.hindu.ui.tests.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by dgaglani on 11/3/14.
 */
public class PropertyFileMapper {

    public static Properties getPropertiesForClass(Class classToGetPropertyFor) {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream(classToGetPropertyFor.getName().toLowerCase() + ".properties");
            // load a properties file
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }
}
