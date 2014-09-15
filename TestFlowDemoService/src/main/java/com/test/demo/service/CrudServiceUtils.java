package com.test.demo.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by dgaglani on 9/14/14.
 */
public class CrudServiceUtils {

    public static HashMap<String, String> getFileContentsAsKeyValueMap(String testCrudFilePath) throws Exception {
        HashMap<String, String> propvals = new HashMap<String, String>();
        Properties properties = new Properties();
        InputStream testFileContentStream = new FileInputStream(testCrudFilePath);
        properties.load(testFileContentStream);
        Set<String> propertyNames = properties.stringPropertyNames();
        for (String Property : propertyNames) {
            System.out.println(Property + ":" + properties.getProperty(Property));
            propvals.put(Property, properties.getProperty(Property));
        }
        return propvals;
    }

    public static void writeToDbFile(Map<String, String> toWriteProperties, String testCrudFilePath) throws Exception {
        FileOutputStream out = new FileOutputStream(testCrudFilePath);
        Properties props = new Properties();
        for (String propKey : toWriteProperties.keySet()) {
            props.setProperty(propKey, toWriteProperties.get(propKey));
        }
        props.store(out, null);
        out.close();
    }
}
