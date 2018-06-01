package com.viasat.exede.mobile.test.common;

import com.viasat.exede.test.framework.propertiesbuilder.PropertyUtil;
import org.apache.poi.util.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * Created by dgaglani on 12/7/2017.
 */
public class FileUtils {

    //This gets set to maven profile variable.
    private static String fileSubPath = "";

    //File Constants
    public static final String LOCATORS_PATH = "locators";
    public static final String TEST_DATA_PATH = "testData";

    private static void setFileSubPath() {
        String mavenProfileFileSubPath = System.getProperty("fileSubPath");
        System.out.println("------Env set to--------: " + mavenProfileFileSubPath);
        if (mavenProfileFileSubPath != null) {
            fileSubPath = mavenProfileFileSubPath + File.separatorChar;
            //TODO - Debug statement. Remove
            System.out.println("fileSubPath:" + fileSubPath);
        } else {
            System.out.println("Env not picked up from maven -D property, looking for env in property file");
            Properties platformAndVersionProps = PropertyUtil.getPropertiesFromInputStream(ClassLoader.getSystemResourceAsStream("PlatformAndVersion.properties"));
            String platform = platformAndVersionProps.getProperty("platform");
            String version = platformAndVersionProps.getProperty("version");
            if (platform.equals("Android") && version.equals("7")) {
                fileSubPath = "7dotO" + File.separatorChar;
            }
            if (platform.equals("iOS") && version.equals("10")) {
                fileSubPath = "iOS" + File.separatorChar;
            }
        }
    }

    private static String getResourceFilePath(URI uri, String filePath) throws Exception {
        File resourceFile = null;
        String fileName = filePath.substring(filePath.lastIndexOf(File.separatorChar) + 1);
        if (uri.toString().startsWith("jar")) {
            System.out.println("AWS execution detected! creating a temp file and then returning that!");
            InputStream is = ClassLoader.getSystemResourceAsStream(filePath);
            resourceFile = getTempFileForInputStream(is, fileName);
        } else {
            resourceFile = new File(uri);
        }
        return resourceFile.getPath();
    }

    private static File getTempFileForInputStream(InputStream is, String fileName) throws Exception {
        File currDir = new File("");
        System.out.println("File name: " + fileName.substring(fileName.lastIndexOf(File.separatorChar) + 1));
        File tempFile = new File(currDir.getAbsolutePath() + File.separatorChar + fileName.substring(fileName.lastIndexOf(File.separatorChar) + 1));
        System.out.println("Temp file absolute path: " + tempFile.getAbsolutePath());
        FileOutputStream out = new FileOutputStream(tempFile);
        IOUtils.copy(is, out);

        return tempFile;
    }

    public static String getNamedFilePath(String fileName) {
        setFileSubPath();
        //Added extension argument to create temp file in aws. This extension argument is *only* used in AWS device farm run

        try {
            String fileNameWithSubPath = fileSubPath + fileName;
            System.out.println("Retrieving resource file at location : " + fileNameWithSubPath);
            URI resourceUri = ClassLoader.getSystemResource(fileNameWithSubPath).toURI();
            String resourceFilePath = getResourceFilePath(resourceUri, fileNameWithSubPath);
            System.out.println("Retrieved resource file from path : " + resourceFilePath);
            return resourceFilePath;
        } catch (Exception e) {
            try {
                System.out.println("No env based file found for : " + fileName + ", returning default file");
                URI resourceUri = ClassLoader.getSystemResource(fileName).toURI();
                System.out.println("File path Full URI " + resourceUri);
                String resourceFilePath = getResourceFilePath(resourceUri, fileName);
                System.out.println("File path: " + resourceFilePath);
                return resourceFilePath;
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    public static void main (String args[]) throws Exception {
        //Test function
        String fileName = "testData" + File.separatorChar + "login.xlsx";
        InputStream is = ClassLoader.getSystemResourceAsStream(fileName);
        File tempFile = getTempFileForInputStream(is, fileName);
        System.out.println(tempFile.getAbsolutePath());
    }
}
