package com.hindu.ui.tests.com.hindu.ui.tests.pageobjects;

import com.hindu.ui.tests.com.hindu.ui.tests.com.hindu.ui.tests.helpers.PropertyFileMapper;
import com.hindu.ui.tests.com.hindu.ui.tests.com.hindu.ui.tests.helpers.StringHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Properties;

/**
 * Created by dgaglani on 11/3/14.
 */
public class SearchResultsPage {

    WebDriver driver;
    Properties searchResultsPageProperties;

    public SearchResultsPage(WebDriver driver, List<String> searchQueries) {
        this.driver = driver;
        searchResultsPageProperties = PropertyFileMapper.getPropertiesForClass(SearchResultsPage.class);
        // Check that we're on the right page.
        if(searchQueries.size() != 1) {
            //For 0 or >1 queries
            if (!driver.getTitle().equals(searchResultsPageProperties.getProperty("MultiSearchQueriesTitle"))) {
                throw new IllegalStateException("Title does not evaluate to " + searchResultsPageProperties.getProperty("Title") + ", page title is : " + driver.getTitle());
            }
        } else {
            //Queries size is 1
            String dynamicTitle = StringHelpers.replacePlaceholderWithString(searchResultsPageProperties.getProperty("SingleSearchQueryTitlePart"), searchQueries.get(0));
            if (!driver.getTitle().equals(dynamicTitle)) {
                throw new IllegalStateException("Title does not evaluate to " + dynamicTitle + ", page title is : " + driver.getTitle());
            }
        }
    }

    public DetailedPropertyListingPage selectPropertyAtIndex(String index) {
        String dynamicSearchResultsLocatorString = StringHelpers.replacePlaceholderWithString(searchResultsPageProperties.getProperty("SearchResultLocator"), index);
        By selectPropertyLocator = By.cssSelector(dynamicSearchResultsLocatorString);
        By propertyNameLocator = By.cssSelector(searchResultsPageProperties.getProperty("SearchResultNameStringLocator"));
        String propertyName = driver.findElement(propertyNameLocator).getText();
        driver.findElement(selectPropertyLocator).click();
        return new DetailedPropertyListingPage(driver, propertyName);
    }
}
