package com.viasat.exede.mobile.test.data;
import org.testng.annotations.DataProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginInfoProvider {

    @DataProvider(name = "InvalidloginDataProvider")
    public static Object[][] provideInvalidLoginData() {
        ExcelFileReader loginXlReader = new ExcelFileReader();
        String dataFileName = "login.xlsx";

        Map<String, String> filterFieldsAndValuesMap = new HashMap<>();
        filterFieldsAndValuesMap.put(ExcelFileReader.SELECTOR_PREFIX + "isValid","NO");
//        filterFieldsAndValuesMap.put("AccountNumber", "302623931");

        loginXlReader.setFilterFieldsAndValues(filterFieldsAndValuesMap);

        List<UserObject> loginTestDatas = loginXlReader.readExcel(dataFileName, "Sheet1");
        Object[][] testData = new Object[loginTestDatas.size()][1];
        int ctr = 0;
        for (UserObject loginTestData : loginTestDatas) {
            testData[ctr][0] = loginTestData;
            ctr++;
        }
        return testData;
    }

    @DataProvider(name = "ValidloginDataProvider")
    public static Object[][] provideValidLoginData() {
        ExcelFileReader loginXlReader = new ExcelFileReader();
        String dataFileName = "login.xlsx";

        Map<String, String> filterFieldsAndValuesMap = new HashMap<>();
        filterFieldsAndValuesMap.put(ExcelFileReader.SELECTOR_PREFIX + "isValid","YES");
//        filterFieldsAndValuesMap.put("AccountNumber", "302623931");

        loginXlReader.setFilterFieldsAndValues(filterFieldsAndValuesMap);

        List<UserObject> loginTestDatas = loginXlReader.readExcel(dataFileName, "Sheet1");
        Object[][] testData = new Object[loginTestDatas.size()][1];
        int ctr = 0;
        for (UserObject loginTestData : loginTestDatas) {
            testData[ctr][0] = loginTestData;
            ctr++;
        }
        return testData;
    }

    @DataProvider(name = "ValidBuyMoreDataProvider")
    public static Object[][] provideValidBuyMoreData() {
        ExcelFileReader loginXlReader = new ExcelFileReader();
        String dataFileName = "login.xlsx";

        Map<String, String> filterFieldsAndValuesMap = new HashMap<>();
        filterFieldsAndValuesMap.put(ExcelFileReader.SELECTOR_PREFIX + "hasBuyMore","YES");
//        filterFieldsAndValuesMap.put("AccountNumber", "302623931");

        loginXlReader.setFilterFieldsAndValues(filterFieldsAndValuesMap);

        List<UserObject> loginTestDatas = loginXlReader.readExcel(dataFileName, "Sheet1");
        Object[][] testData = new Object[loginTestDatas.size()][1];
        int ctr = 0;
        for (UserObject loginTestData : loginTestDatas) {
            testData[ctr][0] = loginTestData;
            ctr++;
        }
        return testData;
    }

    @DataProvider(name = "ValidPayDataProvider")
    public static Object[][] ValidPayDataProvider() {
        ExcelFileReader loginXlReader = new ExcelFileReader();
        String dataFileName = "login.xlsx";

        Map<String, String> filterFieldsAndValuesMap = new HashMap<>();
        filterFieldsAndValuesMap.put(ExcelFileReader.SELECTOR_PREFIX + "PayType","TWO");
//        filterFieldsAndValuesMap.put("AccountNumber", "302623931");

        loginXlReader.setFilterFieldsAndValues(filterFieldsAndValuesMap);

        List<UserObject> loginTestDatas = loginXlReader.readExcel(dataFileName, "Sheet1");
        Object[][] testData = new Object[loginTestDatas.size()][1];
        int ctr = 0;
        for (UserObject loginTestData : loginTestDatas) {
            testData[ctr][0] = loginTestData;
            ctr++;
        }
        return testData;
    }
    @DataProvider(name = "ValidPayProviderActive")
    public static Object[][] ValidPayProviderActive() {
        ExcelFileReader loginXlReader = new ExcelFileReader();
        String dataFileName = "login.xlsx";

        Map<String, String> filterFieldsAndValuesMap = new HashMap<>();
        filterFieldsAndValuesMap.put(ExcelFileReader.SELECTOR_PREFIX + "PayType","ONE");
//        filterFieldsAndValuesMap.put("AccountNumber", "302623931");

        loginXlReader.setFilterFieldsAndValues(filterFieldsAndValuesMap);

        List<UserObject> loginTestDatas = loginXlReader.readExcel(dataFileName, "Sheet1");
        Object[][] testData = new Object[loginTestDatas.size()][1];
        int ctr = 0;
        for (UserObject loginTestData : loginTestDatas) {
            testData[ctr][0] = loginTestData;
            ctr++;
        }
        return testData;
    }
    public static void main(String args[]) {
        LoginInfoProvider.provideInvalidLoginData();
        LoginInfoProvider.provideValidLoginData();
        LoginInfoProvider.provideValidBuyMoreData();
        LoginInfoProvider.ValidPayDataProvider();
        LoginInfoProvider.ValidPayProviderActive();
    }

}
