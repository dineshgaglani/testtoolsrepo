package com.hindu.ui.tests.com.hindu.ui.tests.pageobjects;

import com.hindu.ui.tests.com.hindu.ui.tests.com.hindu.ui.tests.exotel.ExotelOperations;
import com.hindu.ui.tests.com.hindu.ui.tests.com.hindu.ui.tests.helpers.PropertyFileMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Properties;

/**
 * Created by dgaglani on 11/5/14.
 */
public class MobileNumberVerificationPage {

    WebDriver driver;
    Properties mobileNumberVerificationPageProperties;

    public MobileNumberVerificationPage(WebDriver driver) {
        this.driver = driver;
        mobileNumberVerificationPageProperties = PropertyFileMapper.getPropertiesForClass(MobileNumberVerificationPage.class);
        if (!(driver.findElements(By.cssSelector(mobileNumberVerificationPageProperties.getProperty("MissedCallVerificationFormLocator"))).size() > 0)) {
            throw new IllegalStateException("Missed call verification component is not being displayed ");
        }
    }

    public EmailVerificationPage giveMissedCallUsingNum(String phoneNumber) {
        WebElement numToMissedCallTo = driver.findElement(By.cssSelector(mobileNumberVerificationPageProperties.getProperty("NumToSendMissedCallToLocator")));
        String missedCallNum = numToMissedCallTo.getText();
        try {
            ExotelOperations.callToPhone(missedCallNum, phoneNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new EmailVerificationPage(driver);
    }

    public MobileNumberVerificationPage donotGiveMissedCall() {
        try {
            Thread.sleep(1000 * 60 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public EmailVerificationPage retryGivingMissedCall(String phoneNumber) {
        WebElement retryBtn = driver.findElement(By.cssSelector(mobileNumberVerificationPageProperties.getProperty("MissedCallRetryButton")));
        retryBtn.click();
        giveMissedCallUsingNum(phoneNumber);
        return new EmailVerificationPage(driver);
    }
}
