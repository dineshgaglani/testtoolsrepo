package com.viasat.exede.test.framework.propertiesbuilder;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by dgaglani on 12/4/2017.
 */
public class PropertyUtil {

    /* Creates the property file in both class path and project resources so that no recompilation is needed*/
    public static File createPropertyFileObjectForClass(Class clazz) {

        List<File> destinationDirs = Arrays.asList(new File[] {
                //new File(Thread.currentThread().getContextClassLoader().getResource("").getPath()),
                new File(getResourcePath() + "//locators") });

        for (File destinationDir : destinationDirs) {
            assert destinationDir.isDirectory();
            String propertyFileName = clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1) + ".properties";
            File createdPropertyFile = createPropertyFileWithName(destinationDir, propertyFileName);
            return createdPropertyFile;
        }

        return null;
    }

    public static void createPropertyForField(Field field, Properties classProperties) {
        String fieldName = field.getName();
        if (classProperties.getProperty(fieldName) == null) {
            //Create a property with name as field name and value as blank
            classProperties.setProperty(fieldName, "");
        }
    }

    private static File createPropertyFileWithName(File destinationDir, String propertyFileName) {
        File file = new File(destinationDir + File.separator + propertyFileName);
        return file;
    }

    private static String getResourcePath() {
        try {
            URI resourcePathFile = System.class.getResource("/RESOURCE_ROOT_PATH").toURI();
            String resourcePath = Files.readAllLines(Paths.get(resourcePathFile)).get(0);
            URI rootURI = new File("").toURI();
            URI resourceURI = new File(resourcePath).toURI();
            URI relativeResourceURI = rootURI.relativize(resourceURI);
            return relativeResourceURI.getPath();
        } catch (Exception e) {
            return null;
        }
    }

    public static Properties getPropertiesFromInputStream(InputStream is) {
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }

    public static Properties getPropertiesFromFile (String filePath) {
        Properties properties = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream( new File(filePath) );
            // load a properties file
            properties.load(input);
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
        return properties;
    }
}
