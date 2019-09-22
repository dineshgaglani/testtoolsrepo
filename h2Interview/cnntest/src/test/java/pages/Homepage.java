package pages;

import common.PageInitializer;
import common.Utils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Dinesh on 9/20/2019.
 */
public class Homepage {

    public Properties pageProperties;

    WebDriver webDriver;

    public WebElement searchSectionOpener;
    public WebElement sectionsRibbon;

    public Homepage(WebDriver webDriver) {
        Utils.sleep(5 * 1000);
        this.webDriver = webDriver;
        PageInitializer.initializePage(this, webDriver);
    }

    public SearchResultsPage searchFor(String searchString) {
        searchSectionOpener.click();
        WebElement searchTextBox = null;
        WebElement searchButton = null;
        try {
            searchTextBox = (WebElement) PageInitializer.executeMobileElementInitializerFunction(pageProperties.getProperty("searchTextBox"), webDriver);
            searchButton = (WebElement) PageInitializer.executeMobileElementInitializerFunction(pageProperties.getProperty("searchButton"), webDriver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        searchTextBox.sendKeys(searchString);
        searchButton.click();
        return new SearchResultsPage(webDriver);
    }

    public void validateSectionsRibbon(String[] expectedSections) {
        List<String> actualSections = getSectionsFromRibbon(sectionsRibbon);
        Assert.assertArrayEquals("Sections on header are not the same", expectedSections, actualSections.toArray());
    }

    private List<String> getSectionsFromRibbon(WebElement sectionsRibbon) {
        List<WebElement> sections = sectionsRibbon.findElements(By.tagName("li"));
        List<String> stringSections = new ArrayList<>();
        sections.stream().forEach(we -> stringSections.add(we.getText()));
        return stringSections;
    }

}
