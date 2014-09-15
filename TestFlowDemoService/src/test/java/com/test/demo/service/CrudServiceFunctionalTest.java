package com.test.demo.service;

import junit.framework.Assert;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dgaglani on 9/14/14.
 */
public class CrudServiceFunctionalTest {

    String testDatabasePath = System.getProperty("user.home") + System.getProperty("file.separator") + "testCrudFile";

    @Test
    public void testService() {
        DBProvider mockedDbProvider = Mockito.mock(DBProvider.class);
        Mockito.when(mockedDbProvider.provideDb()).thenReturn(testDatabasePath);
        String inputContent = "test3";
        String key = "";
        try {
            key = DBOperator.writeToDb(mockedDbProvider, inputContent, null);
            String outputContent = DBOperator.readFromDb(mockedDbProvider, key);
            Assert.assertTrue(outputContent.equals(inputContent));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception while writing to DB");
        }
        //Verify that nothing else has changed in the file
        verifyFileContents();
        //Update the newly inserted record
        try {
            String updatedInputContent = "test4";
            String updateKey = DBOperator.writeToDb(mockedDbProvider, updatedInputContent, key);
            Assert.assertTrue("Update key not equal to previous key", updateKey.equals(key));
            String updatedOutputContent = DBOperator.readFromDb(mockedDbProvider, key);
            Assert.assertTrue("Updated content not equal to Inserted content, expected: test4, actual: "+updatedOutputContent,updatedOutputContent.equals(updatedInputContent));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception while updating DB");
        }

        try {
            //Then delete the record created for the test
            DBOperator.removeFromDb(mockedDbProvider, key);
            //Check for blank response on read
            String deletedOutputContent = DBOperator.readFromDb(mockedDbProvider, key);
            Assert.assertTrue(deletedOutputContent.equals(""));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception while Deleting DB");
        }
        //Again verify file contents
        verifyFileContents();
    }

    private void verifyFileContents() {
        Map<String, String> fileContents = new HashMap<String, String>();
        try {
            fileContents = CrudServiceUtils.getFileContentsAsKeyValueMap(testDatabasePath);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Error while loading database inside test");
        }
        Assert.assertTrue("File does not contain the original key: test", fileContents.containsKey("test"));
        Assert.assertTrue("File does not contain the original value for key: test, expected: testContent, actual: " + fileContents.get("test"), fileContents.get("test").equals("testContent"));
        Assert.assertTrue("File does not contain the original key: test2", fileContents.containsKey("test2"));
        Assert.assertTrue("File does not contain the original value for key: test2, expected: testContent2, actual: " + fileContents.get("test"), fileContents.get("test2").equals("testContent2"));
    }

}
