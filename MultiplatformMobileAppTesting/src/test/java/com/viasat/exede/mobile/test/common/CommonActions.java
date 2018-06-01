package com.viasat.exede.mobile.test.common;

/*import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AbstractSoftAssertions.*;
*/
//import com.viasat.exede.mobile.test.pages.HomeFooterModule;
//import com.viasat.exede.mobile.test.pages.Profile;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
/*import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.StringAssert;*/
import io.appium.java_client.ios.IOSDriver;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by dgaglani on 1/9/2018.
 */
public class CommonActions {

    public static void scrollDownFully(AndroidDriver driver) {
        System.out.print("--------------Touch Action to scroll called----------------------");
        (new TouchAction(driver)).press(640, 686).moveTo(6, -184).release().perform();
    }

    public static void scrollDownMedium(IOSDriver driver) {
        //(new TouchAction(driver)).press(1212, 854).moveTo(-5, -46).release().perform();       //aws scroll for specific device samsung galaxy s6
           (new TouchAction(driver)).press(9, 403).moveTo(0, -25).release().perform();
    }

    public static void scrollDownMedium(AndroidDriver driver) {
        //(new TouchAction(driver)).press(1212, 854).moveTo(-5, -46).release().perform();       //aws scroll for specific device samsung galaxy s6
        (new TouchAction(driver)).press(9, 403).moveTo(0, -25).release().perform();
    }

    public static void scrollAndTypeInElement(MobileElement mobileElement, AndroidDriver driver, String contentToType) {
        // Java
        while (mobileElement.getAttribute("focused").equals("false"))
        {
            CommonActions.scrollDownMedium(driver);
            mobileElement.click();
        }

        mobileElement.sendKeys(contentToType);
    }


    public static void scrollAndSelectElement(MobileElement mobileElement, AndroidDriver driver) {
        while (!mobileElement.isSelected()) {
            CommonActions.scrollDownMedium(driver);
            mobileElement.click();
        }
    }

    public static String scrollAndReadTextViewElement(MobileElement mobileElement, AndroidDriver driver) {
        while (mobileElement.getText().equals("")) {
            CommonActions.scrollDownMedium(driver);
        }
        return mobileElement.getText();
    }

    public static void scrollAndCheckCheckboxElement(MobileElement mobileElement, AndroidDriver driver, boolean isChecked) {
        //isChecked arg specifies whether the checkbox should be checked or unchecked
        String checkedStr = isChecked ? "true" : "false";
        while (mobileElement.getAttribute("checked").equals(checkedStr)) {
            CommonActions.scrollDownMedium(driver);
            mobileElement.click();
        }

    }


    public static MobileElement scrollAndGetMobileElement(String mobileElementLocatorPropertyValue, IOSDriver driver) {
        MobileElement mobileElement = null;
        try {
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            mobileElement = (MobileElement) PageInitializer.executeMobileElementInitializerFunction(mobileElementLocatorPropertyValue, driver);
        } catch (InvocationTargetException e) {
            if (e.getCause().toString().contains("NoSuchElementException")) {
                System.out.println("Element with locator: " + mobileElementLocatorPropertyValue + " not found, scrolling and trying again.. ");
                // Add a screen capture here
                CommonActions.scrollDownMedium(driver);
                return scrollAndGetMobileElement(mobileElementLocatorPropertyValue, driver);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            Assert.fail();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Assert.fail();
        }
        //driver.findElementByXPath("//android.widget.TextView[contains(@text,\"CURRENT BALANCE\")]/../android.widget.TextView[2]");
        return mobileElement;
    }


    //Temp method:
    public static MobileElement scrollAndGetMobileElement(String mobileElementLocatorPropertyValue, AndroidDriver driver) {
        MobileElement mobileElement = null;
        try {
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            mobileElement = (MobileElement) PageInitializer.executeMobileElementInitializerFunction(mobileElementLocatorPropertyValue, driver);
        } catch (InvocationTargetException e) {
            if (e.getCause().toString().contains("NoSuchElementException")) {
                System.out.println("Element with locator: " + mobileElementLocatorPropertyValue + " not found, scrolling and trying again.. ");
                // Add a screen capture here
                CommonActions.scrollDownMedium(driver);
                return scrollAndGetMobileElement(mobileElementLocatorPropertyValue, driver);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            Assert.fail();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Assert.fail();
        }
        //driver.findElementByXPath("//android.widget.TextView[contains(@text,\"CURRENT BALANCE\")]/../android.widget.TextView[2]");
        return mobileElement;
    }


    public static void findAndClickNextBtnNegativeCase(MobileElement nextBtnMobileElement, AndroidDriver driver, String errorMessageExpected, String attributeToCheckIn) {
        while (driver.findElementsByXPath("//android.view.View[contains(@" + attributeToCheckIn + ",\"" + errorMessageExpected + "\")]").size() == 0) {
            scrollDownMedium(driver);

            //Waiting for "Next >" to appear
            WebDriverWait wait = new WebDriverWait(driver, 3000);
            wait.until(ExpectedConditions.visibilityOf(nextBtnMobileElement));

            nextBtnMobileElement.click();
        }
    }

    public static Object findAndClickBtn(MobileElement mobileElement, AndroidDriver driver, Class nextExpectedPageClass) {

        try {
            scrollDownMedium(driver);


            //Waiting for "Next >" to appear
            WebDriverWait wait = new WebDriverWait(driver, 3000);
//            wait.until(ExpectedConditions.visibilityOf(mobileElement));
            CommonActions.sleep(3 * 1000);

            mobileElement.click();
//            driver.findElementByXPath("//android.view.View[@index='2']");
            return nextExpectedPageClass.getDeclaredConstructor(AndroidDriver.class).newInstance(driver);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            if (e.getCause().toString().contains("PageNotFoundException")) {
                //Reattempt until next page displayed
                return findAndClickBtn(mobileElement, driver, nextExpectedPageClass);
            } else {
                e.printStackTrace();
                return null;
            }
        }


    }

   /* public static void logout(AndroidDriver driver) {

        //Logout
        HomeFooterModule homeFooterModule = new HomeFooterModule(driver);
        Profile profilePage = homeFooterModule.gotoProfile();
        CommonActions.sleep(1 * 1000);
        profilePage.logout();
        CommonActions.sleep(3 * 1000);

    }
*/
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
