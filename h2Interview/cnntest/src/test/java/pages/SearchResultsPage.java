package pages;

import common.PageInitializer;
import common.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Created by Dinesh on 9/20/2019.
 */
public class SearchResultsPage {

    public Properties pageProperties;

    WebDriver webDriver;

    public WebElement searchResultLabel;
    public WebElement sortingCriteriaBtns;
    public WebElement searchResultsList;

    public SearchResultsPage(WebDriver webDriver) {
        Utils.sleep(5 * 1000);
        this.webDriver = webDriver;
        PageInitializer.initializePage(this, webDriver);
    }

    public String getTotalResults() {
        String searchLabelText = searchResultLabel.getText();
        String prefixText = "out of ";
        String totalResults = searchLabelText.substring(searchLabelText.indexOf(prefixText) + prefixText.length(), searchLabelText.indexOf(" for"));
        return totalResults;
    }

    private WebElement getPageFromPageNavigator(WebElement searchResultPageNavigator, String pageNumber) {
        List<WebElement> allPages = searchResultPageNavigator.findElements(By.className("cnnSearchPageLink"));
        WebElement requiredPageWe = allPages.stream().filter(p -> p.getText().equals(pageNumber)).collect(Collectors.toList()).get(0);
        return requiredPageWe;
    }

    public String getResultsRange() {
        String searchLabelText = searchResultLabel.getText();
        String prefixText = "Displaying results ";
        String ranges = searchLabelText.substring(searchLabelText.indexOf(prefixText) + prefixText.length(), searchLabelText.indexOf(" out of"));
        return ranges;
    }

    public String getSearchedText() {
        String searchLabelText = searchResultLabel.getText();
        String prefixText = " for ";
        String searchedText = searchLabelText.substring(searchLabelText.indexOf(prefixText) + prefixText.length());
        return searchedText;
    }

    public String getNoResultsMessage() {
        WebElement searchResultNoResults = null;
        try {
            searchResultNoResults = (WebElement) PageInitializer.executeMobileElementInitializerFunction(pageProperties.getProperty("searchResultNoResults"), webDriver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchResultNoResults.getText();
    }

    public SearchResultsPage changeSortingCriteria(String targetSortingCriteria) {
        sortingCriteriaBtns.click();
        List<WebElement> allSortingCriteria = sortingCriteriaBtns.findElements(By.tagName("li"));
        WebElement targetSortingCriteriaWebElement = allSortingCriteria.stream().filter(we -> we.getText().equals(targetSortingCriteria)).collect(Collectors.toList()).get(0);
        targetSortingCriteriaWebElement.click();
        return new SearchResultsPage(webDriver);
    }

    public Map<String, String> getSearchResultAttribs(Integer searchResultIndex) {
        Map<String, String> searchResultItemsMap = new HashMap<>();
        WebElement searchResult = searchResultsList.findElements(By.className("cnn-search__result")).get(searchResultIndex);
        searchResultItemsMap.put("Headline", searchResult.findElement(By.className("cnn-search__result-headline")).getText());
        searchResultItemsMap.put("date", searchResult.findElement(By.className("cnn-search__result-publish-date")).getText());
        return searchResultItemsMap;
    }
}
