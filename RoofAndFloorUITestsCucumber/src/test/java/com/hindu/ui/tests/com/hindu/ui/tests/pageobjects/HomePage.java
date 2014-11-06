package com.hindu.ui.tests.com.hindu.ui.tests.pageobjects;

import com.hindu.ui.tests.com.hindu.ui.tests.com.hindu.ui.tests.helpers.PropertyFileMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by dgaglani on 11/3/14.
 */
public class HomePage {

    WebDriver driver;
    Properties homePageProperties;
    //For search
    List<String> searchQueries = new ArrayList<String>();

    public HomePage(WebDriver driver) {
        this.driver = driver;
        homePageProperties = PropertyFileMapper.getPropertiesForClass(HomePage.class);
        // Check that we're on the right page.
        if (!driver.getTitle().equals(homePageProperties.getProperty("Title"))) {
           throw new IllegalStateException("Title does not evaluate to " + homePageProperties.getProperty("Title") + ", page title is : " + driver.getTitle());
        }
    }

    public HomePage enterAreaForSearch(String area) {
        By searchBoxLocator = By.cssSelector(homePageProperties.getProperty("SearchBoxLocator"));
        driver.findElement(searchBoxLocator).sendKeys(area);
        searchQueries.add(area);
        return this;
    }

    public SearchResultsPage submitSearchQuery() {
        By submitSearchButtonLocator = By.cssSelector(homePageProperties.getProperty("SearchSubmitButtonLocator"));
        driver.findElement(submitSearchButtonLocator).click();
        return new SearchResultsPage(driver, searchQueries);
    }


}
