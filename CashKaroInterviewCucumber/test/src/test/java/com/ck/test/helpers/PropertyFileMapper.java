package com.ck.test.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.ck.test.pages.Homepage;

/**
 * Created by dgaglani on 11/3/14.
 */
public class PropertyFileMapper {

    public static Properties getPropertiesForClass(Class classToGetPropertyFor) {
        Properties prop = new Properties();
        InputStream input = null;

        try {
        	ClassLoader loader = Thread.currentThread().getContextClassLoader();
            input = loader.getResourceAsStream(classToGetPropertyFor.getSimpleName() + ".properties");
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
