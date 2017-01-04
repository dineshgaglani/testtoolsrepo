package com.ck.test.pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ck.test.AppTest;
import com.ck.test.helpers.PropertyFileMapper;

public class LoginWithFacebookPage {

	WebDriver driver;
	Properties loginWithFbProperties;

	public LoginWithFacebookPage(WebDriver driver) {
		AppTest.waiting();
		this.driver = driver;

		// Store the current window handle
		String winHandleBefore = driver.getWindowHandle();

		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		loginWithFbProperties = PropertyFileMapper.getPropertiesForClass(LoginWithFacebookPage.class);
		if (!(driver.findElements(By.cssSelector(loginWithFbProperties.getProperty("pageIdentifier"))).size() > 0)) {
			throw new IllegalStateException("Login With Facebook is not being displayed ");
		}

		// Close the new window, if that window no more required
		driver.close();

		// Switch back to original browser (first window)
		driver.switchTo().window(winHandleBefore);

	}

}
