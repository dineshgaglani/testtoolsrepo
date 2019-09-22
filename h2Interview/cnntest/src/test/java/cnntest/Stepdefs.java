package cnntest;

import api.StockPriceClient;
import common.DriverProvider;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.Homepage;
import pages.SearchResultsPage;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Stepdefs {

    WebDriver webDriver = DriverProvider.intializeDriver();
    private Homepage homepage;
    private SearchResultsPage searchResultsPage;

    private StockPriceClient stockPriceClient;

    Float amazonPrice;
    Float applePrice;

    @Given("I navigate to the cnn homepage")
    public void i_navigate_to_the_cnn_homepage() {
        // Write code here that turns the phrase above into concrete actions
        webDriver.get("https://www.cnn.com");
        homepage = new Homepage(webDriver);
    }

    @Then("I validate all the sections in the sections ribbon")
    public void i_validate_all_the_sections_in_the_sections_ribbon() {
        // Write code here that turns the phrase above into concrete actions
        homepage.validateSectionsRibbon(new String[] {"US", "World", "Politics", "Business", "Opinion", "Health", "Entertainment", "Style", "Travel", "Sports", "Videos"});
    }

    @When("I search for {string}")
    public void i_search_for(String searchString) {
        // Write code here that turns the phrase above into concrete actions
        searchResultsPage = homepage.searchFor(searchString);
    }

    @Then("I see results {string} for {string} on search results page")
    public void i_see_results_for_on_search_results_page(String searchResultRange, String searchString) {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals("Result ranges do not match ", searchResultRange, searchResultsPage.getResultsRange());
        Assert.assertEquals("Searched string does not match ", searchString, searchResultsPage.getSearchedText());
    }

    @Then("I validate total results are more than {string}")
    public void i_validate_total_results_are_more_than(String minResults) {
        // Write code here that turns the phrase above into concrete actions
        String totalResults = searchResultsPage.getTotalResults();
        Assert.assertTrue("Total results less than min results : " + minResults, Integer.parseInt(totalResults.trim()) > Integer.parseInt(minResults));
    }

    @Then("I see no results")
    public void i_see_no_results() {
        // Write code here that turns the phrase above into concrete actions
        String expectedText = "did not match any documents";
        Assert.assertTrue("No results string does not contain expected text: " + expectedText, searchResultsPage.getNoResultsMessage().contains(expectedText));
    }

    @When("I navigate to cnn search results page {string} for {string}")
    public void i_navigate_to_cnn_search_results_page_for(String searchPage, String searchString) {
        // Write code here that turns the phrase above into concrete actions
        WebDriver webDriver = DriverProvider.intializeDriver();
        String searchStringEncoded = URLEncoder.encode(searchString);
        Integer from = Integer.parseInt(searchPage) * 10 - 10;
        webDriver.get("https://www.cnn.com/search?size=10&q=" + searchStringEncoded + "&page=" + searchPage + "&from=" + from);
        searchResultsPage = new SearchResultsPage(webDriver);
    }

    @When("I change the sorting criteria to {string}")
    public void i_change_the_sorting_criteria_to(String sortingCriteria) {
        // Write code here that turns the phrase above into concrete actions
        searchResultsPage = searchResultsPage.changeSortingCriteria(sortingCriteria);
    }

    @Then("I should the result at index {string} should be newer than the result at {string}")
    public void i_should_the_result_at_index_should_be_newer_than_the_result_at(String firstSearchResult, String secondSearchResult) {
        // Write code here that turns the phrase above into concrete actions
        Map<String, String> firstSearchResultAttribs = searchResultsPage.getSearchResultAttribs(Integer.parseInt(firstSearchResult));
        Map<String, String> secondSearchResultAttribs = searchResultsPage.getSearchResultAttribs(Integer.parseInt(secondSearchResult));
        SimpleDateFormat resultDateFormat = new SimpleDateFormat("MMM dd, yyyy");
        try {
            Date result1Date = resultDateFormat.parse(firstSearchResultAttribs.get("date"));
            Date result2Date = resultDateFormat.parse(secondSearchResultAttribs.get("date"));

            Assert.assertTrue("First date not more recent than second date", result1Date.after(result2Date) || result1Date.equals(result2Date));
        } catch (ParseException e) {
            e.printStackTrace();
            Assert.fail("Error parsing dates from cnn website");
        }
    }


    @When("I query for {string} and {string}")
    public void i_query_for_and(String stock1, String stock2) {
        // Write code here that turns the phrase above into concrete actions
        stockPriceClient = new StockPriceClient();
        amazonPrice = stockPriceClient.getHighForStock(stock1);
        applePrice = stockPriceClient.getHighForStock(stock2);
    }

    @Then("I validate the price of {string} is higher than that of {string}")
    public void i_validate_the_price_of_is_higher_than_that_of(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue("Amazon price is lower than apple price", amazonPrice > applePrice);
    }

    @Then("I close the browser")
    public void i_close_the_browser() {
        // Write code here that turns the phrase above into concrete actions
        webDriver.close();
    }

    public static void main (String args[]) {
        Stepdefs sd = new Stepdefs();
        sd.i_navigate_to_cnn_search_results_page_for("2", "machine learning");
        sd.i_see_results_for_on_search_results_page("11-20", "machine learning");
        sd.i_change_the_sorting_criteria_to("Date");
        sd.i_see_results_for_on_search_results_page("1-10", "machine learning");
        sd.i_should_the_result_at_index_should_be_newer_than_the_result_at("2", "3");

//        sd.i_query_for_and("AMZN", "AAPL");
//        sd.i_validate_the_price_of_is_higher_than_that_of("AMZN", "AAPL");
    }

}
