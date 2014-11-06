package com.hindu.ui.tests.com.hindu.ui.tests.pageobjects;

import com.hindu.ui.tests.com.hindu.ui.tests.com.hindu.ui.tests.helpers.PropertyFileMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Properties;

/**
 * Created by dgaglani on 11/3/14.
 */
public class EmailVerificationPage {

    WebDriver driver;
    Properties emailVerificationPageProperties;
    WebElement emailVerificationForm;

    public EmailVerificationPage(WebDriver driver) {
        this.driver = driver;
        emailVerificationPageProperties = PropertyFileMapper.getPropertiesForClass(EmailVerificationPage.class);
        if (!(driver.findElements(By.cssSelector(emailVerificationPageProperties.getProperty("EmailVerificationPageFormLocator"))).size() > 0)) {
            throw new IllegalStateException("Not on Email verification page");
        }
    }

    public EmailVerificationPage clickOnResendEmail() throws Exception{
        throw new Exception("Feature not implemented");
    }

    public EmailVerificationPage fillInOtpCodeBox(String otpCode) {
        WebElement otpInputBox = driver.findElement(By.cssSelector(emailVerificationPageProperties.getProperty("OtpInputBoxLocator")));
        otpInputBox.sendKeys(otpCode);
        return this;
    }

    public EmailVerificationPage submitOtpCode(String otpCode) {
        fillInOtpCodeBox(otpCode);
        WebElement otpSubmitBtn = driver.findElement(By.cssSelector(emailVerificationPageProperties.getProperty("OtpSubmitButtonLocator")));
        otpSubmitBtn.click();
        //Should return conditionally based on otpCode, will return same page if OTP is invalid, else will return another page
        return this;
    }

    public SellerDetailsPage skipEmailVerification() {
        WebElement skipVerificationBtn = driver.findElement(By.cssSelector(emailVerificationPageProperties.getProperty("SkipVerificationLocator")));
        skipVerificationBtn.click();
        return new SellerDetailsPage(driver);
    }

}
