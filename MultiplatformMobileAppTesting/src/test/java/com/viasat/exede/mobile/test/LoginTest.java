package com.viasat.exede.mobile.test;

/*import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AbstractSoftAssertions.*;*/

import com.viasat.exede.mobile.test.common.CommonActions;
import com.viasat.exede.mobile.test.common.DriverProvider;
import com.viasat.exede.mobile.test.data.LoginInfoProvider;
import com.viasat.exede.mobile.test.data.UserObject;
import com.viasat.exede.mobile.test.pages.*;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
//import org.assertj.core.api.SoftAssertions;
import io.appium.java_client.ios.IOSDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;

public class LoginTest {


    //AndroidDriver driver;
    IOSDriver driver;

    @BeforeTest
    public void openApp() throws MalformedURLException {
        driver = DriverProvider.intializeDriver();

    }

    @AfterTest
    public void closeApp() {
        DriverProvider.removeApp(driver);
    }

/*    @Test(dataProviderClass = com.viasat.exede.mobile.test.data.LoginInfoProvider.class, dataProvider = "InvalidloginDataProvider")
    public void testInvalidLogin(UserObject userObject) {
        Login loginPage = new Login(driver);
        loginPage.invalidLogin(userObject.getUsername(), userObject.getPassword());
        Assert.assertTrue(driver.findElementsByXPath("//android.view.View[contains(@content-desc,\"" + userObject.getExpectedError() +"\")]").size() > 0, "Error message expected : " + userObject.getExpectedError() + ", but was not visible ");
    }
*/


    @Test(dataProviderClass = LoginInfoProvider.class, dataProvider = "ValidloginDataProvider")
    public void testValidLogin(UserObject userObject) {
        Login loginPage = new Login(driver);
        PostLoginLanding postLoginLandingPage = loginPage.validLogin(userObject.getUsername(), userObject.getPassword());
        CommonActions.sleep(5 * 1000);
        //Validations in post login landing page
      /*   SoftAssert softAssert = new SoftAssert();
        String accountInfoUserNameExpected = "Name: " + userObject.getFirstName() + " " + userObject.getLastName();
        String accountInfoUserNameActual = postLoginLandingPage.getAccountInformationName();

        softAssert.assertTrue(accountInfoUserNameExpected.equals(accountInfoUserNameActual), getAssertionFailureMessage("User Name in account information", accountInfoUserNameExpected, accountInfoUserNameActual, userObject.getUsername()));
        //Boolean val=accountInfoUserNameExpected.equals(accountInfoUserNameActual);
        // SoftAssertions sft=new SoftAssertions();
        //sft.assertThat(val).overridingErrorMessage(getAssertionFailureMessage("User Name in account information", accountInfoUserNameExpected, accountInfoUserNameActual, userObject.getUsername())).isTrue();


        String accountInfoAccountNumExpected = "Account Number: " + userObject.getAccountNumber();
        String accountInfoAccountNumActual = postLoginLandingPage.getAccountInformationAccountNum();
        softAssert.assertTrue(accountInfoAccountNumExpected.equals(accountInfoAccountNumActual), getAssertionFailureMessage("Account number in account information", accountInfoAccountNumExpected, accountInfoAccountNumActual, userObject.getUsername()));


        String accountInfoAccountStatusExpected = "Account Status: " + userObject.getAccountStatus();
        String accountInfoAccountStatusActual = postLoginLandingPage.getAccountInformationStatus();
        softAssert.assertTrue(accountInfoAccountStatusExpected.equals(accountInfoAccountStatusActual), getAssertionFailureMessage("Account status in account information", accountInfoAccountStatusExpected, accountInfoAccountStatusActual, userObject.getUsername()));


        String currentBalanceExpected = userObject.getCurrentBalance();
        String currentBalanceActual = postLoginLandingPage.getCurrentBalance();
        softAssert.assertTrue(currentBalanceExpected.equals(currentBalanceActual), getAssertionFailureMessage("Current balance", currentBalanceExpected, currentBalanceActual, userObject.getUsername()));

        String nextBillExpected = userObject.getNextBill();
        String nextBillActual = postLoginLandingPage.getNextBill();
        softAssert.assertTrue(nextBillExpected.equals(nextBillActual), getAssertionFailureMessage("Next bill", nextBillExpected, nextBillActual, userObject.getUsername()));

        String lastPaymentDateAmtExpected = userObject.getLastBillDateAndAmount();

        if (lastPaymentDateAmtExpected.isEmpty() == false) {
            String lastPaymentDateAmtActual = postLoginLandingPage.getLastBillDateAndAmount();
            softAssert.assertTrue(lastPaymentDateAmtExpected.equals(lastPaymentDateAmtActual), getAssertionFailureMessage("Last Payment Date and Amount", lastPaymentDateAmtExpected, lastPaymentDateAmtActual, userObject.getUsername()));
        }


        String usageUsedExpected = userObject.getUsageUsed();
        String usageUsedActual = postLoginLandingPage.getUsageUsed();
        softAssert.assertTrue(usageUsedExpected.equals(usageUsedActual), getAssertionFailureMessage("Used usage", usageUsedExpected, usageUsedActual, userObject.getUsername()));

        String usageMaxExpected = userObject.getUsageMax();
        String usageMaxActual = postLoginLandingPage.getUsageMax();
        softAssert.assertTrue(usageMaxExpected.equals(usageMaxActual), getAssertionFailureMessage("Max usage allowed", usageMaxExpected, usageMaxActual, userObject.getUsername()));

        String usageEndExpected = userObject.getUsageEndDate();
        String usageEndActual = postLoginLandingPage.getUsageEndDate();
        softAssert.assertTrue(usageEndActual.contains(usageEndExpected), getAssertionFailureMessage("Usage End Date", usageEndExpected, usageEndActual, userObject.getUsername()));

        //Assertions on Your plan Page
        String yourPlanExpected = userObject.getYourPlan();
        if (yourPlanExpected.isEmpty() == false) {
            String yourPlanActual = postLoginLandingPage.getYourPlan();
            softAssert.assertTrue(yourPlanExpected.equals(yourPlanActual), getAssertionFailureMessage("Your Plan", yourPlanExpected, yourPlanActual, userObject.getUsername()));
        }


       String priorityDataExpected = userObject.getPriorityData();
        String priorityDataActual = postLoginLandingPage.getPriorityData();
        softAssert.assertTrue(priorityDataExpected.equals(priorityDataActual), getAssertionFailureMessage("Priority Data", priorityDataExpected, priorityDataActual, userObject.getUsername()));

       String downloadSpeedExpected = userObject.getDownloadSpeed();
        String downloadSpeedActual = postLoginLandingPage.getDownloadSpeed();
        softAssert.assertTrue(downloadSpeedExpected.equals(downloadSpeedActual), getAssertionFailureMessage("Download Speed ", downloadSpeedExpected, downloadSpeedActual, userObject.getUsername()));


        /*  Obtain Legal documentation and terms and conditions

       HomeFooterModule homeFooterModule = new HomeFooterModule(driver);
       Profile profilePage = homeFooterModule.gotoProfile();
       CommonActions.sleep(1 * 1000);
       profilePage.gotoTermsAndConditionsPage();
       Profile profilePage1 = homeFooterModule.gotoProfile();
       profilePage1.gotoPrivacyPolicyPage

        /*

        //Assertions of Wifi
        String WifiExpected = userObject.getWififName();
        if (WifiExpected.isEmpty() == false) {
            String WifiActual = postLoginLandingPage.getWifiName();
            softAssert.assertTrue(WifiExpected.equals(WifiActual), getAssertionFailureMessage("Wi-Fi Name :", WifiExpected, WifiActual, userObject.getUsername()));
        }

*/
        //CommonActions.logout(driver);
      //  softAssert.assertAll();
        //sft.assertAll();


    }
//
//
//    @Test(dataProviderClass = com.viasat.exede.mobile.test.data.LoginInfoProvider.class, dataProvider = "ValidBuyMoreDataProvider")
//            public void testBuyMore(UserObject userObject) {
//                //TODO - Separate test class
//                //TODO - conventional data provider(ONly for accounts having buy more option)--added selector for same in login excel.
//
//                Login loginPage = new Login(driver);
//                PostLoginLanding postLoginLandingPage = loginPage.validLogin(userObject.getUsername(), userObject.getPassword());
//                CommonActions.sleep(8 * 1000);
//
//                BuyMoreData buyMoreDataPage = postLoginLandingPage.performBuyMoreAction();
//                buyMoreDataPage.performBuyMoreAction("7");//try 7 for nxt run
//                CommonActions.logout(driver);
//
//            }
//
//
//      @Test(dataProviderClass = com.viasat.exede.mobile.test.data.LoginInfoProvider.class, dataProvider = "ValidloginDataProvider")
//        public void testUserProfile(UserObject userObject) {
//            SoftAssert softAssert = new SoftAssert();
//            Login loginPage = new Login(driver);
//            PostLoginLanding postLoginLandingPage = loginPage.validLogin(userObject.getUsername(), userObject.getPassword());
//            CommonActions.sleep(2 * 1000);
//            //Initialize the HomeFooterModule
//            HomeFooterModule homeFooterModule = new HomeFooterModule(driver);
//            CommonActions.sleep(3 * 1000);
//            Profile profilePage = homeFooterModule.gotoProfile();
//
//            CommonActions.sleep(3 * 1000);
//
//            //Validate the values on the profile page
//            String accountNameExpected = "Account Name: " + userObject.getFirstName() + " " + userObject.getLastName();
//            String accountNameActual = profilePage.getAccountName().trim();
//            softAssert.assertTrue(accountNameExpected.equals(accountNameActual), getAssertionFailureMessage("Account name in profile page", accountNameExpected, accountNameActual, userObject.getUsername()));
//
//            //Validate the values on the profile page
//            String accountNumberExpected = "Account No: " + userObject.getAccountNumber();
//            String accountNumberActual = profilePage.getAccountNumber().trim();
//            softAssert.assertTrue(accountNumberExpected.equals(accountNumberActual), getAssertionFailureMessage("Account number in profile page", accountNumberExpected, accountNumberActual, userObject.getUsername()));
//
//            //Validate the values on the profile page
//            String modemMacExpected = "Modem MAC: " + userObject.getModemMac();
//            String modemMacActual = profilePage.getModemMac().trim();
//            softAssert.assertTrue(modemMacExpected.equals(modemMacActual), getAssertionFailureMessage("Modem MAC in profile page", modemMacExpected, modemMacActual, userObject.getUsername()));
//
//            //Validate the values on the profile page
//            String contactNumberExpected = "Contact Number: " + userObject.getContactNumber();
//            String contactNumberActual = profilePage.getContactNumber().trim();
//            softAssert.assertTrue(contactNumberExpected.equals(contactNumberActual), getAssertionFailureMessage("Contact number in profile page", contactNumberExpected, contactNumberActual, userObject.getUsername()));
//
//            //Validate the values on the profile page
//            String emailExpected = "Email: " + userObject.getEmail();
//            String emailActual = profilePage.getEmail().trim();
//            softAssert.assertTrue(emailExpected.equals(emailActual), getAssertionFailureMessage("Email in profile page", emailExpected, emailActual, userObject.getUsername()));
//
//            //Validate the values on the profile page
//            String serviceAddressExpected = "Service Address: " + userObject.getServiceAddress();
//            String serviceAddressActual = profilePage.getServiceAddress().trim();
//            softAssert.assertTrue(serviceAddressExpected.equals(serviceAddressActual), getAssertionFailureMessage("Service Address in profile page", serviceAddressExpected, serviceAddressActual, userObject.getUsername()));
//
//
//            Profile profilePage1 = homeFooterModule.gotoProfile();
//            CommonActions.sleep(1 * 1000);
//            profilePage1.logout();
//
//        }
//
//
    private String getAssertionFailureMessage(String fieldBeingAsserted, String expectedValue, String actualValue, String Username) {
        return "\n " + fieldBeingAsserted + " does not match expected value, expected: \"" + expectedValue + "\", actual: \"" + actualValue + "\"" + "for username: " + Username;
    }


}
