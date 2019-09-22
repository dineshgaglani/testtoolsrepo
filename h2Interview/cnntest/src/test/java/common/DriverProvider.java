package common;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverProvider {

    public static WebDriver intializeDriver() {
//        System.setProperty("webdriver.chrome.driver", "c:\\ChromeDriver\\chromedriver.exe");
        // Initialize browser
//        WebDriver driver=new ChromeDriver();
        WebDriver driver=initializeRemoteDriver();
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver initializeRemoteDriver() {
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setPlatform(Platform.LINUX);
        cap.setVersion("");
        WebDriver remoteDriver = null;
        try {
            remoteDriver = new RemoteWebDriver(new URL("http://localhost:" + System.getenv("HUB_PORT") + "/wd/hub"), cap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        remoteDriver.manage().window().maximize();

        return remoteDriver;
    }

}
