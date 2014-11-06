package com.hindu.ui.tests.com.hindu.ui.tests.pageobjects;

import com.hindu.ui.tests.com.hindu.ui.tests.com.hindu.ui.tests.helpers.PropertyFileMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

/**
 * Created by dgaglani on 11/3/14.
 */
public class DetailedPropertyListingPage {

    WebDriver driver;
    Properties detailedPropertyListingPageProperties;

    public DetailedPropertyListingPage(WebDriver driver, String selectedPropertyName) {
        this.driver = driver;
        if (!driver.getTitle().equals(selectedPropertyName + " Chennai")) {
            throw new IllegalStateException("Title does not evaluate to " + selectedPropertyName + " Chennai" + ", page title is : " + driver.getTitle());
        }
        detailedPropertyListingPageProperties = PropertyFileMapper.getPropertiesForClass(HomePage.class);
    }

    public ContactSellerPage contactSeller() {
        By contactSellerBtnLocator = By.cssSelector(detailedPropertyListingPageProperties.getProperty("ContactSellerBtnLocator"));
        driver.findElement(contactSellerBtnLocator).click();
        return new ContactSellerPage(driver);
    }

    public boolean isListingContacted() {
        By findContactedLocator = By.cssSelector(detailedPropertyListingPageProperties.getProperty("IsContactedLocator"));
        return driver.findElement(findContactedLocator).getAttribute("class").contains("hide");
    }


}
