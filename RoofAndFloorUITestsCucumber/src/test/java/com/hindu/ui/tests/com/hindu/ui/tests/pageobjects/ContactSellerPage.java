package com.hindu.ui.tests.com.hindu.ui.tests.pageobjects;

import com.hindu.ui.tests.com.hindu.ui.tests.com.hindu.ui.tests.helpers.PropertyFileMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Properties;

/**
 * Created by dgaglani on 11/3/14.
 */
public class ContactSellerPage {

    WebDriver driver;
    Properties contactSellerPageProperties;
    WebElement phoneNumberFieldLocator;
    WebElement editPhoneNumberBtnLocator;
    WebElement emailFieldLocator;

    public ContactSellerPage(WebDriver driver) {
        this.driver = driver;
        contactSellerPageProperties = PropertyFileMapper.getPropertiesForClass(HomePage.class);
        if (!(driver.findElements(By.cssSelector(contactSellerPageProperties.getProperty("ContactSellerComponentLocator"))).size() > 0)) {
            throw new IllegalStateException("Contact Seller component is not being displayed ");
        }
        phoneNumberFieldLocator = driver.findElement(By.cssSelector(contactSellerPageProperties.getProperty("PhoneNumberFieldLocator")));
    }

    public boolean isMobileFieldEntered() {
        String phoneNumberFieldDisabledAttribute = phoneNumberFieldLocator.getAttribute("disabled");
        if(phoneNumberFieldDisabledAttribute != null) {
            return phoneNumberFieldDisabledAttribute.equals("disabled");
        }
        return false;
    }

    public ContactSellerPage enterMobileNumber(String mobileNumber) {
        if(isMobileFieldEntered()) {
             editMobileNumber(mobileNumber);
        }
        else {
            phoneNumberFieldLocator.sendKeys(mobileNumber);
        }
        return this;
    }

    public ContactSellerPage editMobileNumber(String mobileNumber) {
        editPhoneNumberBtnLocator = driver.findElement(By.cssSelector(contactSellerPageProperties.getProperty("EditMobileNumberFieldLocator")));
        editPhoneNumberBtnLocator.click();
        phoneNumberFieldLocator.sendKeys(mobileNumber);
        return this;
    }

    public boolean isEmailEntered() {
        return isMobileFieldEntered();
    }

    public ContactSellerPage enterEmail(String emailId) {
        emailFieldLocator = driver.findElement(By.cssSelector(contactSellerPageProperties.getProperty("EmailFieldLocator")));
        if(isMobileFieldEntered()) {
            editEmail(emailId);
        }
        else {
            emailFieldLocator.sendKeys(emailId);
        }
        return this;
    }

    public ContactSellerPage editEmail(String emailId) {
        if(driver.findElements(By.cssSelector(contactSellerPageProperties.getProperty("EditMobileNumberFieldLocator"))).size() > 0) {
            editPhoneNumberBtnLocator = driver.findElement(By.cssSelector(contactSellerPageProperties.getProperty("EditMobileNumberFieldLocator")));
            editPhoneNumberBtnLocator.click();
        }
        emailFieldLocator.sendKeys(emailId);
        return this;
    }

    public EmailVerificationPage submitEmailAndMobileSubsequentTime() {
        WebElement sendEmailBtn = driver.findElement(By.cssSelector(contactSellerPageProperties.getProperty("SendEmailBtnLocator")));
        sendEmailBtn.click();
        return new EmailVerificationPage(driver);
    }

    public MobileNumberVerificationPage submitEmailAndMobile() {
        WebElement sendEmailBtn = driver.findElement(By.cssSelector(contactSellerPageProperties.getProperty("SendEmailBtnLocator")));
        sendEmailBtn.click();
        return new MobileNumberVerificationPage(driver);
    }
}
