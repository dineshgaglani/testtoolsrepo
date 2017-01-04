package com.ck.test.pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ck.test.AppTest;
import com.ck.test.helpers.PropertyFileMapper;

public class JoinFreeNowPage {
	
	WebDriver driver;
	
	TopBarComponent topBarComponent;
	
	WebElement fullName;
	WebElement emailAddress;
	WebElement confirmEmail;
	WebElement password;
	WebElement passwordText;
	
	WebElement loginWithFbButton;
	
	Properties JoinFreeNowPageProperties;
	
	public JoinFreeNowPage(WebDriver driver) {
		this.driver = driver;
		
		JoinFreeNowPageProperties = PropertyFileMapper.getPropertiesForClass(JoinFreeNowPage.class);
        if (!(driver.findElements(By.cssSelector(JoinFreeNowPageProperties.getProperty("pageIdentifier"))).size() > 0)) {
            throw new IllegalStateException("Join free now page is not being displayed ");
        }
        
        fullName = driver.findElement(By.cssSelector(JoinFreeNowPageProperties.getProperty("fullName")));
        emailAddress = driver.findElement(By.cssSelector(JoinFreeNowPageProperties.getProperty("emailAddress")));
        confirmEmail = driver.findElement(By.cssSelector(JoinFreeNowPageProperties.getProperty("confirmEmail")));
        password = driver.findElement(By.cssSelector(JoinFreeNowPageProperties.getProperty("password")));
        passwordText = driver.findElement(By.cssSelector(JoinFreeNowPageProperties.getProperty("passwordText")));
        
        topBarComponent = new TopBarComponent(driver);
	}
	
	public JoinFreeNowPage enterDetails(String fullNameInput, String emailInput, String confirmEmailInput, String passwordInput) {
		fullName.sendKeys(fullNameInput);
		emailAddress.sendKeys(emailInput);
		confirmEmail.sendKeys(confirmEmailInput);
		
		//Hack
		String script = "document.getElementById('" + JoinFreeNowPageProperties.getProperty("passwordText").substring(1) + "').setAttribute('value','" + passwordInput + "')";
		((JavascriptExecutor) driver).executeScript(script, passwordText);
		String script2 = "document.getElementById('" + JoinFreeNowPageProperties.getProperty("password").substring(1) + "').setAttribute('value','" + passwordInput + "')";
		((JavascriptExecutor) driver).executeScript(script2, password);
		
		AppTest.waiting();
		
		return this;
	}
	
	public LoginWithFacebookPage clickOnLoginWithFb() {
		loginWithFbButton = driver.findElement(By.cssSelector(JoinFreeNowPageProperties.getProperty("loginWithFbButton")));
		loginWithFbButton.click();
		return new LoginWithFacebookPage(driver);
	}
	
	public LoginDialog clickOnSignIn() {
		return topBarComponent.clickOnSignIn();
	}

}
