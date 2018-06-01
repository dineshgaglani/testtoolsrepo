package com.viasat.exede.mobile.test.pages;

import com.viasat.exede.mobile.test.common.CommonActions;
import com.viasat.exede.mobile.test.common.PageInitializer;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.util.List;
import java.util.Properties;

/**
 * Created by dgaglani on 12/1/2017.
 */
public class Login {

   // public AndroidDriver androidDriver;
public IOSDriver iosDriver;
    public MobileElement usernameElement;
    public MobileElement passwordElement;
    public MobileElement loginButton;
    public MobileElement registerLink;

    public Properties pageProperties;

    public Login(IOSDriver driver) {
        //this.androidDriver = driver;
        this.iosDriver=driver;

        System.out.println("Sleeping 30 seconds..");
        CommonActions.sleep(30 * 1000);
        System.out.println("Finished sleeping 30 seconds");

        String pageSource = iosDriver.getPageSource();
        System.out.println("Found Page source");
        System.out.println("Page Source: " + pageSource);

        PageInitializer.initializePage(this, driver);
        //Validate page content
    }

    private void performLoginAction(String username, String password) {
        iosDriver.hideKeyboard();
        usernameElement.clear();
        usernameElement.sendKeys(username);

        System.out.println("Entered text in username field..");

        //Keyboard appears in AWS device farm, hide it.
      //  androidDriver.hideKeyboard();
        iosDriver.hideKeyboard();
        passwordElement.clear();
        passwordElement.sendKeys(password);

        //Keyboard appears in AWS device farm, hide it.
        //androidDriver.hideKeyboard();
        iosDriver.hideKeyboard();
        loginButton.click();
    }

  public PostLoginLanding validLogin(String username, String password) {
        performLoginAction(username, password);
        CommonActions.sleep(3 * 1000);

        MobileElement agreeButton = CommonActions.scrollAndGetMobileElement(pageProperties.getProperty("agree"), iosDriver);
        agreeButton.click();

        CommonActions.sleep(3 * 1000);
        MobileElement signupButton=CommonActions.scrollAndGetMobileElement(pageProperties.getProperty("signupButton"),iosDriver);
        signupButton.click();


        return new PostLoginLanding(iosDriver);
    }
 /*
    public FindAccount clickRegisterLink() {
        CommonActions.scrollDownFully(androidDriver);
        registerLink.click();
        CommonActions.sleep(3 * 1000);
        return new FindAccount(androidDriver);
    }

    public Login invalidLogin(String username, String password) {
        performLoginAction(username, password);
        return new Login(androidDriver);
    }
*/
}
