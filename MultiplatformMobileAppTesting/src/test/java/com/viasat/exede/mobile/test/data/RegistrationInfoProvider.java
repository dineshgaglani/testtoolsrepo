package com.viasat.exede.mobile.test.data;

import org.testng.annotations.DataProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dgaglani on 12/6/2017.
 */
public class RegistrationInfoProvider {

    @DataProvider(name = "registrationDataProvider")
    public static Object[][] provideAllRegistrationData() {
        ExcelFileReader registrationXlReader = new ExcelFileReader();
        String dataFileName = "registration.xlsx";

        Map<String, String> filterFieldsAndValuesMap = new HashMap<>();
        filterFieldsAndValuesMap.put(ExcelFileReader.SELECTOR_PREFIX + "isValid","True");
//        filterFieldsAndValuesMap.put("AccountNumber", "302623931");

        registrationXlReader.setFilterFieldsAndValues(filterFieldsAndValuesMap);

        List<UserObject> registrationTestDatas = registrationXlReader.readExcel(dataFileName, "Sheet1");
        Object[][] testData = new Object[registrationTestDatas.size()][1];
        int ctr = 0;
        for (UserObject registrationTestData : registrationTestDatas) {
            testData[ctr][0] = registrationTestData;
            ctr++;
        }
        return testData;
    }

    @DataProvider(name = "InvalidAccountRegistrationDataProvider")
    public static Object[][] getInvalidAccountRegistrationData() {
        ExcelFileReader registrationXlReader = new ExcelFileReader();
        String dataFileName = "registration.xlsx";

        Map<String, String> filterFieldsAndValuesMap = new HashMap<>();
        filterFieldsAndValuesMap.put(ExcelFileReader.SELECTOR_PREFIX + "isValid","False");
        filterFieldsAndValuesMap.put(ExcelFileReader.SELECTOR_PREFIX + "errorPageName","FindAccount");

        registrationXlReader.setFilterFieldsAndValues(filterFieldsAndValuesMap);

        List<UserObject> InvalidAccountRegistrationTestDatas = registrationXlReader.readExcel(dataFileName, "Sheet1");
        Object[][] testData = new Object[InvalidAccountRegistrationTestDatas.size()][1];
        int ctr = 0;
        for (UserObject registrationTestData : InvalidAccountRegistrationTestDatas) {
            testData[ctr][0] = registrationTestData;
            ctr++;
        }
        return testData;
    }


    public static void main(String args[]) {
        RegistrationInfoProvider.getInvalidAccountRegistrationData();
    }

}
