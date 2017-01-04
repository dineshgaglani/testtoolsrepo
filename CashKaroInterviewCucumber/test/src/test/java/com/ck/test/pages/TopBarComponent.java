package com.ck.test.pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ck.test.helpers.PropertyFileMapper;

public class TopBarComponent {
	
	WebDriver driver;
	WebElement joinFreeButton;
	WebElement signInButton;
	
	WebElement myAccountButton;
	
	Properties topBarProperties;
	
	public TopBarComponent(WebDriver driver) {
		this.driver = driver;
		
		topBarProperties = PropertyFileMapper.getPropertiesForClass(TopBarComponent.class);
        if (!(driver.findElements(By.cssSelector(topBarProperties.getProperty("pageIdentifier"))).size() > 0)) {
            throw new IllegalStateException("top bar component is not being displayed ");
        }
        
        joinFreeButton = driver.findElement(By.cssSelector(topBarProperties.getProperty("joinFreeButton")));
	}
	
	public JoinFreeNowPage clickJoinFree() {
		joinFreeButton.click();
		return new JoinFreeNowPage(driver);
	}
	
	public LoginDialog clickOnSignIn() {
		signInButton = driver.findElement(By.cssSelector(topBarProperties.getProperty("signInButton")));
		signInButton.click();
		return new LoginDialog(driver);
	}
	
	public boolean isMyAccountVisible() {
		myAccountButton = driver.findElement(By.cssSelector(topBarProperties.getProperty("myAccountButton")));
		return myAccountButton.isDisplayed();
	}

}
