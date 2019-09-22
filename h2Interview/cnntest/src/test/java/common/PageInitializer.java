package common;

import common.exceptions.PageNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.Homepage;
import propertiesbuilder.ClassReader;
import propertiesbuilder.PropertyUtil;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;

/**
 * Created by dgaglani on 12/1/2017.
 */
public class PageInitializer {

    public static void initializePage(Object pageObject, WebDriver driver) {
        Properties properties = getPageProperties (pageObject.getClass());

        //Set page object properties
        setPageObjectProperty(pageObject, properties);

        //Initialize the MobileElement properties of the page object using the read property file
        List<Field> pageMobileElements = ClassReader.parseClassFields("org.openqa.selenium.WebElement", pageObject.getClass());
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

    public static Object executeMobileElementInitializerFunction(String mobileElementProperty, WebDriver driver) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String[] mobileElementPropertySplit = mobileElementProperty.split("#Split#");
        Object byInvocationResult = By.class.getDeclaredMethod(mobileElementPropertySplit[0], String.class).invoke(null, mobileElementPropertySplit[1]);
        Method findElementMethod = driver.getClass().getMethod("findElement", By.class);
        return findElementMethod.invoke(driver, byInvocationResult);
    }

    public static void setMobileElementValue(Field mobileElementField, String mobileElementProperty, Object pageObject, WebDriver driver) {
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
        PageInitializer.getPageProperties(Homepage.class);
    }


}
