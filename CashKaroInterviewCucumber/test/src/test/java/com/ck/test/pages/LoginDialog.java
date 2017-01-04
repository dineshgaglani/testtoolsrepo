package com.ck.test.pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ck.test.AppTest;
import com.ck.test.helpers.PropertyFileMapper;

public class LoginDialog {
	
	WebDriver driver;
	
	Properties loginDialogProperties;
	
	WebElement joinWithFacebookButton;
	
	WebElement email;
	WebElement password;
	WebElement passwordText;
	WebElement signInButton;
	WebElement invalidLoginMessage;
	
	public LoginDialog(WebDriver driver) {
		this.driver = driver;
		
		AppTest.waiting();
		
		loginDialogProperties = PropertyFileMapper.getPropertiesForClass(LoginDialog.class);
		
        if (!(driver.findElements(By.cssSelector(loginDialogProperties.getProperty("pageIdentifier"))).size() > 0)) {
            throw new IllegalStateException("Login Dialog is not being displayed ");
        }
        
        driver.switchTo().frame(driver.findElement(By.cssSelector(loginDialogProperties.getProperty("pageIdentifier"))));
        email = driver.findElement(By.cssSelector(loginDialogProperties.getProperty("email")));
        password = driver.findElement(By.cssSelector(loginDialogProperties.getProperty("password")));
        passwordText = driver.findElement(By.cssSelector(loginDialogProperties.getProperty("passwordText")));
        
        signInButton = driver.findElement(By.cssSelector(loginDialogProperties.getProperty("signInButton")));
	}
	
	public LoginWithFacebookPage clickOnLoginWithFb() {
		joinWithFacebookButton = driver.findElement(By.cssSelector(loginDialogProperties.getProperty("joinWithFacebookButton")));
		joinWithFacebookButton.click();
		return new LoginWithFacebookPage(driver);
	}
	
	public Homepage validLogin(String emailInput, String passwordInput) {
		enterDetails(emailInput, passwordInput);
		signInButton.click();
		return new Homepage(driver);
	}
	
	public LoginDialog invalidLogin(String emailInput, String passwordInput) {
		enterDetails(emailInput, passwordInput);
		signInButton.click();
		
		AppTest.waiting();
		AppTest.waiting();
		
		//Optionally, can switch to main context and return new instance of login dialog
		return this;
	}
	
	private void enterDetails(String emailInput, String passwordInput) {
		email.sendKeys(emailInput);
		
		//Hack
		String script = "document.getElementById('" + loginDialogProperties.getProperty("passwordText").substring(1) + "').setAttribute('value','" + passwordInput + "')";
		((JavascriptExecutor) driver).executeScript(script, passwordText);
		String script2 = "document.getElementById('" + loginDialogProperties.getProperty("password").substring(1) + "').setAttribute('value','" + passwordInput + "')";
		((JavascriptExecutor) driver).executeScript(script2, password);
		
		AppTest.waiting();
	}
	
	public boolean isInvalidPassMessageDisplayed() {
		invalidLoginMessage = driver.findElement(By.cssSelector(loginDialogProperties.getProperty("invalidLoginMessage")));
		return invalidLoginMessage.isDisplayed();
	}

}
