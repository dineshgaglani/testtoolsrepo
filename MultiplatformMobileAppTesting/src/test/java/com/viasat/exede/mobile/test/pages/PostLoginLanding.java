package com.viasat.exede.mobile.test.pages;

import com.viasat.exede.mobile.test.common.CommonActions;
import com.viasat.exede.mobile.test.common.PageInitializer;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

/**
 * Created by dgaglani on 12/1/2017.
 */
public class PostLoginLanding {

    private AndroidDriver androidDriver;
    private IOSDriver iosDriver;

//    public MobileElement diagnosticSectionParent;

    public Properties pageProperties;


    public PostLoginLanding(IOSDriver driver) {
        this.iosDriver = driver;
        PageInitializer.initializePage(this, driver);
        //Validate page content

    }

    public String getAccountInformationName() {
        MobileElement accountInformationName = CommonActions.scrollAndGetMobileElement(pageProperties.getProperty("accountInformationName"), androidDriver);
        return accountInformationName.getText();
    }

    public String getAccountInformationAccountNum () {
        MobileElement accountInformationAccountNum = CommonActions.scrollAndGetMobileElement(pageProperties.getProperty("accountInformationAccountNum"), androidDriver);
        return accountInformationAccountNum.getText();
    }

    public String getAccountInformationStatus() {
        MobileElement accountInformationStatus = CommonActions.scrollAndGetMobileElement(pageProperties.getProperty("accountInformationStatus"), androidDriver);
        return accountInformationStatus.getText();
    }
    public String getUsageUsed () {
        MobileElement usageUsed = CommonActions.scrollAndGetMobileElement(pageProperties.getProperty("usageUsed"), androidDriver);
        return usageUsed.getText();
    }

    public String getUsageMax () {
        MobileElement usageMax = CommonActions.scrollAndGetMobileElement(pageProperties.getProperty("usageMax"), androidDriver);
        return usageMax.getText();
    }

    public String getUsageEndDate() {
        MobileElement usageEndDate = CommonActions.scrollAndGetMobileElement(pageProperties.getProperty("usageEndDate"), androidDriver);
        return usageEndDate.getText();
    }

    public void enableVideoDataExtender() {
        MobileElement videoDataExtenderToggle = CommonActions.scrollAndGetMobileElement(pageProperties.getProperty("videoDataExtenderToggle"), androidDriver);
        CommonActions.scrollAndCheckCheckboxElement(videoDataExtenderToggle, androidDriver, true);

    }
/*
    public BuyMoreData performBuyMoreAction () {

        MobileElement buyDataBtn = CommonActions.scrollAndGetMobileElement(pageProperties.getProperty("buyMoreBtn"), androidDriver);
        buyDataBtn.click();
        CommonActions.sleep(1*1000);


        return new BuyMoreData(androidDriver);
    }


    public PayBill performPayBillAction () {
        MobileElement PayBillButton = CommonActions.scrollAndGetMobileElement(pageProperties.getProperty("payBill"), androidDriver);
        PayBillButton.click();
        return new PayBill(androidDriver);
    }

    public ExpiredPayBill goToPayBill () {
        MobileElement PayBillButton = CommonActions.scrollAndGetMobileElement(pageProperties.getProperty("payBill"), androidDriver);
       // CommonActions.scrollDownMedium(androidDriver);
        PayBillButton.click();
        return new ExpiredPayBill(androidDriver);
    }

    public String getCurrentBalance () {
        MobileElement currentBalance = CommonActions.scrollAndGetMobileElement(pageProperties.getProperty("currentBalance"), androidDriver);
        return currentBalance.getText();
    }

    public String getWifiName() {
        MobileElement wifiName = CommonActions.scrollAndGetMobileElement(pageProperties.getProperty("wifiName"), androidDriver);
        return wifiName.getText();
    }

    public String getNextBill() {
        MobileElement nextBill = CommonActions.scrollAndGetMobileElement(pageProperties.getProperty("nextBill"), androidDriver);
        return nextBill.getText();
    }

    public String getLastBillDateAndAmount () {
        MobileElement lastPaymentDateAndAmount = CommonActions.scrollAndGetMobileElement(pageProperties.getProperty("lastPaymentDateAndAmount"), androidDriver);
        return lastPaymentDateAndAmount.getText();
    }

    public String getYourPlan () {
        CommonActions.scrollDownFully(androidDriver);
        MobileElement yourPlan = CommonActions.scrollAndGetMobileElement(pageProperties.getProperty("yourPlan"), androidDriver);
        return yourPlan.getText();
    }

    public String getPriorityData () {
        MobileElement priorityData = CommonActions.scrollAndGetMobileElement(pageProperties.getProperty("priorityData"), androidDriver);
        return priorityData.getText();
    }

    public String getDownloadSpeed () {
        MobileElement downloadSpeed = CommonActions.scrollAndGetMobileElement(pageProperties.getProperty("downloadSpeed"), androidDriver);
        return downloadSpeed.getText();
    }

    public YourPlan getPlanDetails () {

        CommonActions.sleep(5*5000);
        CommonActions.scrollDownFully(androidDriver);
        MobileElement planDetails = CommonActions.scrollAndGetMobileElement(pageProperties.getProperty("planDetails"), androidDriver);
        planDetails.click();
        CommonActions.sleep(1*1000);
        return new YourPlan(androidDriver);
    }

*/
}
