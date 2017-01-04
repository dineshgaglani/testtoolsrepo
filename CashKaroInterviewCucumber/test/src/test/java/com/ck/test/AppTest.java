package com.ck.test;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.ck.test.pages.Homepage;
import com.ck.test.pages.JoinFreeNowPage;
import com.ck.test.pages.LoginDialog;
import com.ck.test.pages.LoginWithFacebookPage;
import com.ck.test.pages.TopBarComponent;

import cucumber.api.CucumberOptions;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

/**
 * Unit test for simple App.
 */
@CucumberOptions(features = "json:target/cucumber-report.json")
public class AppTest {

	private WebDriver driver;

	// Pages
	Homepage homepage;
	JoinFreeNowPage joinPage;
	LoginDialog loginDialog;
	LoginWithFacebookPage facebookLoginPage;

	// Inputs
	String name;
	String email;
	String confirmEmail;
	String password;

	private void initialize() {
		System.setProperty("webdriver.chrome.driver", "chromedriver");

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));
		this.driver = new ChromeDriver(capabilities);
	}

	@Given("^I am logged in my facebook account$")
	public void i_am_logged_in_my_facebook_account() throws Throwable {
		// Ideally open fb and check if user is logged in
	}

	public static void main(String[] args) throws Throwable {
		AppTest app = new AppTest();
		app.i_navigate_to_homepage();
		app.i_click_on_sign_in_on_top_bar();
		app.i_enter_something_in_email_on_login_dialog("goxodzh@sharklasers.com");
		app.i_enter_something_in_password_on_login_dialog("password12");
		app.i_click_on_signin_Invalid_on_login_dialog();
		app.i_verify_i_the_invalid_login_message_appears_on_the_login_dialog();
		System.out.println("Ended!");
		app.driver.close();
	}

	@When("^I navigate to homepage")
	public void i_navigate_to_homepage() throws Throwable {
		String homepageUrl = "http://cashkaro.iamsavings.co.uk/";
		initialize();
		driver.get(homepageUrl);
		homepage = new Homepage(driver);
	}

	@When("^I navigate to Join Now page")
	public void i_navigate_to_join_now_page() throws Throwable {
		String joinNowPageUrl = "https://cashkaro.iamsavings.co.uk/join-free-now";
		initialize();
		driver.get(joinNowPageUrl);
		joinPage = new JoinFreeNowPage(driver);
	}

	@Then("^I verify that I am on the Join Free Now page$")
	public void i_verify_that_i_am_on_the_join_free_now_page() throws Throwable {
		// Verification done in constructor, this step is written for
		// readability
	}

	@Then("^I verify that the login with facebook page popsup$")
	public void i_verify_that_the_login_with_facebook_page_popsup() throws Throwable {
		// Verification done in constructor, this step is written for
		// readability
	}

	@Then("^I verify that the login dialog pops up$")
	public void i_verify_that_the_login_dialog_pops_up() throws Throwable {
		// Verification done in constructor, this step is written for
		// readability
	}

	@And("^I click on Join Free on Cashback section$")
	public void i_click_on_join_free_on_cashback_section() throws Throwable {
		joinPage = homepage.clickJoinFreeOnCashbackSection();
	}

	@And("^I click on Join Free on Top Bar section$")
	public void i_click_on_join_free_on_top_bar_section() throws Throwable {
		joinPage = homepage.clickJoinFreeOnTopBar();
	}

	@And("^I enter \"([^\"]*)\" in full name$")
	public void i_enter_something_in_full_name(String strArg1) throws Throwable {
		name = strArg1;
	}

	@And("^I enter \"([^\"]*)\" in email address$")
	public void i_enter_something_in_email_address(String strArg1) throws Throwable {
		email = strArg1;
	}

	@And("^I enter \"([^\"]*)\" in confirm email$")
	public void i_enter_something_in_confirm_email(String strArg1) throws Throwable {
		confirmEmail = strArg1;
	}

	@And("^I enter \"([^\"]*)\" in password$")
	public void i_enter_something_in_password(String strArg1) throws Throwable {
		password = strArg1;
		joinPage.enterDetails(name, email, confirmEmail, password);
	}

	@And("^I click on Join With Facebook$")
	public void i_click_on_join_with_facebook() throws Throwable {
		facebookLoginPage = joinPage.clickOnLoginWithFb();
	}

	@And("^I click on Sign In$")
	public void i_click_on_sign_in() throws Throwable {
		loginDialog = joinPage.clickOnSignIn();
	}

	@And("^I click on Join With Facebook on login dialog$")
	public void i_click_on_join_with_facebook_on_login_dialog() throws Throwable {
		facebookLoginPage = loginDialog.clickOnLoginWithFb();
	}

	@Then("^I verify I am logged in$")
	public void i_verify_i_am_logged_in() throws Throwable {
		Assert.assertTrue( "Login unsuccessful, my account button not shown", homepage.isUserLoggedIn());
	}

	@Then("^I verify I the Invalid Login Message appears on the login dialog$")
	public void i_verify_i_the_invalid_login_message_appears_on_the_login_dialog() throws Throwable {
		Assert.assertTrue("Invalid login, error message not shown!" , loginDialog.isInvalidPassMessageDisplayed());
	}

	@And("^I click on Sign In on top bar$")
	public void i_click_on_sign_in_on_top_bar() throws Throwable {
		loginDialog = homepage.getTopBarComponent().clickOnSignIn();
	}

	@And("^I enter \"([^\"]*)\" in Email on login dialog$")
	public void i_enter_something_in_email_on_login_dialog(String strArg1) throws Throwable {
		email = strArg1;
	}

	@And("^I enter \"([^\"]*)\" in Password on login dialog$")
	public void i_enter_something_in_password_on_login_dialog(String strArg1) throws Throwable {
		password = strArg1;
	}

	@And("^I click on SignIn on login dialog$")
	public void i_click_on_signin_on_login_dialog() throws Throwable {
		homepage = loginDialog.validLogin(email, password);
	}

	@And("^I click on SignIn Invalid on login dialog$")
	public void i_click_on_signin_Invalid_on_login_dialog() throws Throwable {
		loginDialog = loginDialog.invalidLogin(email, password);
	}
	
	@After
	public void afterScenario() {
	  driver.close();
	}

	public static void waiting() {

		try {
			System.out.print("Waiting");

			int count = 4;
			int counter = 0;
			while (counter < count) {
				System.out.print(".");
				Thread.sleep(1 * 1000);
				counter++;
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
