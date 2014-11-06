package com.hindu.ui.tests.com.hindu.ui.tests.pageobjects;

import com.hindu.ui.tests.com.hindu.ui.tests.com.hindu.ui.tests.helpers.PropertyFileMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

/**
 * Created by dgaglani on 11/3/14.
 */
public class SellerDetailsPage {

    WebDriver driver;
    Properties sellerDetailsPageProperties;

    public SellerDetailsPage(WebDriver driver) {
        this.driver = driver;
        sellerDetailsPageProperties = PropertyFileMapper.getPropertiesForClass(SellerDetailsPage.class);
        if (!(driver.findElements(By.cssSelector(sellerDetailsPageProperties.getProperty("SellerDetailsComponentLocator"))).size() > 0)) {
            throw new IllegalStateException("Not on Seller Details page");
        }
    }

    public void closeSellerDetailsPage() {
        driver.findElement(By.cssSelector(sellerDetailsPageProperties.getProperty("CloseSellerDetailsPageBtnLocator"))).click();
    }

}
