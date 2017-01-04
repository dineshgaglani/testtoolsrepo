package com.ck.test.pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ck.test.AppTest;
import com.ck.test.helpers.PropertyFileMapper;

public class Homepage {
	
	WebDriver driver;
	
	TopBarComponent topBarComponent;
	WebElement joinFreeButton;
	
	Properties homepageProperties;
	
	public Homepage(WebDriver driver) {
		AppTest.waiting();
		this.driver = driver;
		
		homepageProperties = PropertyFileMapper.getPropertiesForClass(Homepage.class);
        if (!(driver.findElements(By.cssSelector(homepageProperties.getProperty("pageIdentifier"))).size() > 0)) {
            throw new IllegalStateException("Homepage is not being displayed ");
        }
        
        topBarComponent = new TopBarComponent(driver);
        
	}
	
	public TopBarComponent getTopBarComponent() {
		return topBarComponent;
	}
	
	public JoinFreeNowPage clickJoinFreeOnTopBar() {
		return topBarComponent.clickJoinFree();
	}
	
	public JoinFreeNowPage clickJoinFreeOnCashbackSection() {
		joinFreeButton = driver.findElement(By.cssSelector(homepageProperties.getProperty("joinFreeButton")));
		joinFreeButton.click();
		return new JoinFreeNowPage(driver);
	}
	
	public LoginDialog clickOnSignIn() {
		return topBarComponent.clickOnSignIn();
	}
	
	public boolean isUserLoggedIn() {
		return topBarComponent.isMyAccountVisible();
	}
	
}
