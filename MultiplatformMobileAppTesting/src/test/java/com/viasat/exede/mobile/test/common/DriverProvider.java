package com.viasat.exede.mobile.test.common;

import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverProvider {

    public static IOSDriver intializeDriver() throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
      //  dc.setCapability("device", "Android");             //cmt for aws
        //dc.setCapability("deviceName", "device");         //cmt for aws
        //dc.setCapability("platformName", "Android");     //cmt for aws
        dc.setCapability("newCommandTimeout", 60 * 5);
        //dc.setCapability("app", "C:\\Nehal\\MobileAutomation\\apk\\ViaSat.apk");  //cmt for aws


       // AndroidDriver driver = null;
        IOSDriver driver=null;
//        try {
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), dc);

//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        return driver;
    }

    public static void removeApp(AndroidDriver driver) {
//        driver.removeApp("C:\\Nehal\\MobileAutomation\\apk\\ViaSatMobileApp_qa (8).apk");
    }
    public static void removeApp(IOSDriver driver) {
//        driver.removeApp("C:\\Nehal\\MobileAutomation\\apk\\ViaSatMobileApp_qa (8).apk");
    }
}
