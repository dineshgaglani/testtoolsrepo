package com.hindu.ui.tests; /**
 * Created by dgaglani on 11/1/14.
 */

import com.hindu.ui.tests.com.hindu.ui.tests.pageobjects.*;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import sun.security.util.PendingException;

import java.util.Arrays;

// Associates Cucumber-JVM with the JUnit runner
@RunWith(Cucumber.class)
public class ContactSellerTest {

    private String baseUrl = "https://qa.roofandfloor.com";
    private WebDriver driver;
    String phoneNumberBeingUsedToGiveMissedCall;
    //Pages
    HomePage homePage;
    SearchResultsPage searchResultsPage;
    DetailedPropertyListingPage detailedPropertyListingPage;
    ContactSellerPage contactSellerPage;
    EmailVerificationPage emailVerificationPage;
    SellerDetailsPage sellerDetailsPage;


    @Given("^I navigate to the roofandfloor homepage$")
    public void I_navigate_to_the_roofandfloor_homepage() throws Throwable {
        // Express the Regexp above with the code you wish you had
        /*DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));
        this.driver = new ChromeDriver(capabilities);*/
        this.driver = new HtmlUnitDriver();
        driver.get(baseUrl);
        homePage = new HomePage(driver);
    }

    @When("^I search for property in area \"([^\"]*)\"$")
    public void I_search_for_property_in_area(String arg1) throws Throwable {
        // Express the Regexp above with the code you wish you had
        searchResultsPage = homePage.enterAreaForSearch(arg1).submitSearchQuery();
    }

    @Then("^I verify that I am on the Search Results page$")
    public void I_verify_that_I_am_on_the_search_results_page() throws Throwable {
        // Express the Regexp above with the code you wish you had
       //Verification happens during invocation itself, this can be left blank
    }

    @Given("^I am on the Search Results page$")
    public void I_am_on_the_search_results_page() throws Throwable {
        // Express the Regexp above with the code you wish you had
        I_verify_that_I_am_on_the_search_results_page();
    }

    @Given("^I click on property at index \"([^\"]*)\"$")
    public void I_click_on_property_at_index(String arg1) throws Throwable {
        // Express the Regexp above with the code you wish you had
        detailedPropertyListingPage = searchResultsPage.selectPropertyAtIndex(arg1);
    }

    @Then("^I verify that I am on the Detailed Property Listing page$")
    public void I_verify_that_I_am_on_the_Detailed_Property_Listing_page() throws Throwable {
        // Express the Regexp above with the code you wish you had
        //Verification happens during invocation, leave blank
    }

    @Given("^I am on the Detailed Property Listing page$")
    public void I_am_on_the_Detailed_Property_Listing_page() throws Throwable {
        // Express the Regexp above with the code you wish you had
        I_verify_that_I_am_on_the_Detailed_Property_Listing_page();
    }

    @When("^I click on contact seller$")
    public void I_click_on_contact_seller() throws Throwable {
        // Express the Regexp above with the code you wish you had
        contactSellerPage = detailedPropertyListingPage.contactSeller();
    }

    @Then("^I verify that I am on the Contact Seller page$")
    public void I_verify_that_I_am_on_the_Contact_Seller_page() throws Throwable {
        // Express the Regexp above with the code you wish you had

    }

    @Given("^I am on the Contact Seller page$")
    public void I_am_on_the_Contact_Seller_page() throws Throwable {
        // Express the Regexp above with the code you wish you had
        I_verify_that_I_am_on_the_Contact_Seller_page();
    }

    @Given("^enter the number \"([^\"]*)\" and email id \"([^\"]*)\"$")
    public void enter_the_number_and_email_id(String arg1, String arg2) throws Throwable {
        // Express the Regexp above with the code you wish you had
        if(contactSellerPage.isMobileFieldEntered()) {
            contactSellerPage.editMobileNumber(arg1).editEmail(arg2);
        } else {
            contactSellerPage.enterMobileNumber(arg1).enterEmail(arg2);
        }
        phoneNumberBeingUsedToGiveMissedCall = arg1;
    }

    @Given("^click on Send Email$")
    public void click_on_Send_Email() throws Throwable {
        // Express the Regexp above with the code you wish you had
        emailVerificationPage = contactSellerPage.submitEmailAndMobile().giveMissedCallUsingNum(phoneNumberBeingUsedToGiveMissedCall);
    }

    @Then("^I verify that I am on the Email Verification page$")
    public void I_verify_that_I_am_on_the_Email_Verification_page() throws Throwable {
        // Express the Regexp above with the code you wish you had
    }

    @Given("^I am on the Email Verification page$")
    public void I_am_on_the_Email_Verification_page() throws Throwable {
        // Express the Regexp above with the code you wish you had
        I_verify_that_I_am_on_the_Email_Verification_page();
    }

    @Given("^I click on Skip verification$")
    public void I_click_on_Skip_verification() throws Throwable {
        // Express the Regexp above with the code you wish you had
        emailVerificationPage.skipEmailVerification();
    }

    @Then("^I verify that I am on the Seller Details page$")
    public void I_verify_that_I_am_on_the_Seller_Details_page() throws Throwable {
        // Express the Regexp above with the code you wish you had

    }

    @Given("^I am on the Seller Details page$")
    public void I_am_on_the_Seller_Details_page() throws Throwable {
        // Express the Regexp above with the code you wish you had
        I_verify_that_I_am_on_the_Seller_Details_page();
    }

    @Given("^I close the Seller details page$")
    public void I_close_the_Seller_details_page() throws Throwable {
        // Express the Regexp above with the code you wish you had
        sellerDetailsPage.closeSellerDetailsPage();
    }


    @When("^I click on the \"([^\"]*)\" tab$")
    public void I_click_on_the_tab(String arg1) throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }

    @When("^I click on a property at the co-ordinate \"([^\"]*)\" and \"([^\"]*)\"$")
    public void I_click_on_a_property_at_the_co_ordinate_and(String arg1, String arg2) throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }

}
