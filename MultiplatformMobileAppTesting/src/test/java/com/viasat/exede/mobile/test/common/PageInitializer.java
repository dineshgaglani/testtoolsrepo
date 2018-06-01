package com.viasat.exede.mobile.test.common;

import android.util.Property;
import com.viasat.exede.mobile.test.common.exceptions.PageNotFoundException;
import com.viasat.exede.mobile.test.pages.Login;
import com.viasat.exede.test.framework.propertiesbuilder.ClassReader;
import com.viasat.exede.test.framework.propertiesbuilder.PropertyUtil;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Properties;

/**
 * Created by dgaglani on 12/1/2017.
 */
public class PageInitializer {

    public static void initializePage(Object pageObject, IOSDriver driver) {
        Properties properties = getPageProperties (pageObject.getClass());

        //Set page object properties
        setPageObjectProperty(pageObject, properties);

        //Initialize the MobileElement properties of the page object using the read property file
        List<Field> pageMobileElements = ClassReader.parseClassFields("io.appium.java_client.MobileElement", pageObject.getClass());
        pageMobileElements.forEach(mobileElement -> setMobileElementValue(mobileElement, properties.getProperty(mobileElement.getName()), pageObject, driver));

    }

    public static void setPageObjectProperty (Object pageObject, Properties properties) {
        try {
            Field pagePropertyField = pageObject.getClass().getField("pageProperties");
            pagePropertyField.set(pageObject, properties);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Properties getPageProperties (Class pageClass) {
        String pageName = pageClass.getName().substring(pageClass.getName().lastIndexOf(".") + 1);

        //Read the general property file for the given page
        Properties properties = getPropertiesForNamedFile(pageName);

        //Read the env variable based property file for the given page
        Properties envBasedProperties = getPropertiesForEnvNamedFile(pageName);

        //Merge the contents of 2 property files, overwriting properties in general properties with the ones in envBasedProperties
        Properties overwrittenPropeties = overwriteProperties(properties, envBasedProperties);

        return overwrittenPropeties;
    }

    private static Properties overwriteProperties(Properties propertiesToOverwrite, Properties overwriterProperties) {

        for ( String propertyToOverwrite : overwriterProperties.stringPropertyNames() ) {
            String overwrittenPropertyValue = overwriterProperties.getProperty(propertyToOverwrite);
            propertiesToOverwrite.setProperty(propertyToOverwrite, overwrittenPropertyValue);
        }

        return propertiesToOverwrite;
    }

    public static Object executeMobileElementInitializerFunction(String mobileElementProperty, IOSDriver driver) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String[] mobileElementPropertySplit = mobileElementProperty.split("#Split#");
        Method findElementMethod = driver.getClass().getDeclaredMethod("findElementBy" + mobileElementPropertySplit[0], String.class);
        return findElementMethod.invoke(driver, mobileElementPropertySplit[1]);
    }

    public static Object executeMobileElementInitializerFunction(String mobileElementProperty, AndroidDriver driver) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String[] mobileElementPropertySplit = mobileElementProperty.split("#Split#");
        Method findElementMethod = driver.getClass().getDeclaredMethod("findElementBy" + mobileElementPropertySplit[0], String.class);
        return findElementMethod.invoke(driver, mobileElementPropertySplit[1]);
    }
    public static void setMobileElementValue(Field mobileElementField, String mobileElementProperty, Object pageObject, IOSDriver driver) {
        if (mobileElementProperty == null || mobileElementProperty.trim().equals("")) {
            System.out.println("Locator not found for: " + mobileElementField.getName() + " from page: " + pageObject.getClass());
            return;
        }

        try {
            if (mobileElementField.get(pageObject) == null) {
                //Set field from property file only if not set already
                Object findElementInvocationResult = executeMobileElementInitializerFunction(mobileElementProperty, driver);
                mobileElementField.set(pageObject, findElementInvocationResult);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            if (e.getCause().toString().contains("NoSuchElementException")) {
                throw new PageNotFoundException("page " + pageObject.getClass() + " not rendered correctly since element : " + mobileElementProperty + " of mobileElement : " + mobileElementField.getName() + " is not found ");
            }
            else {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static Properties getPropertiesForEnvNamedFile(String name) {
        Properties properties = new Properties();
        try {
            properties = PropertyUtil.getPropertiesFromFile(FileUtils.getNamedFilePath(FileUtils.LOCATORS_PATH + File.separatorChar + name + ".properties"));
        } catch (Exception e) {
            System.out.println("No env based properties for page name: " + name);
        }
        return properties;
    }

    public static Properties getPropertiesForNamedFile(String name) {
        try {
            return PropertyUtil.getPropertiesFromInputStream(Thread.currentThread().getContextClassLoader().getResourceAsStream(FileUtils.LOCATORS_PATH + File.separatorChar + name + ".properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String args[]) {
        PageInitializer.getPageProperties(Login.class);
    }


}
