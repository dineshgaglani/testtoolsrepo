package com.viasat.exede.mobile.test.data;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.viasat.exede.mobile.test.common.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dgaglani on 12/6/2017.
 */
public class ExcelFileReader {

    private Map<String, String> filterFieldsAndValues;

    public Map<String, String> getFilterFieldsAndValues() {
        return filterFieldsAndValues;
    }

    public static final String SELECTOR_PREFIX = "Selector_";

    public void setFilterFieldsAndValues(Map<String, String> filterFieldsAndValues) {
        this.filterFieldsAndValues = filterFieldsAndValues;
    }

    public List<UserObject> readExcel(String fileName, String sheetName) {
        //TODO - Read excel using filter fields and values
        String excelFilePath = FileUtils.getNamedFilePath(FileUtils.TEST_DATA_PATH + File.separatorChar + fileName);
        Fillo fillo = new Fillo();
        Connection excelConnection = null;

        try {

            excelConnection = fillo.getConnection(excelFilePath);

            //Creating query with filters from filterFieldsAndValuesMap
            String generatedQuery = "Select * from " + sheetName;
            if (filterFieldsAndValues != null) {
                generatedQuery = generatedQuery + " where ";
                for (String key : filterFieldsAndValues.keySet()) {
                    generatedQuery = generatedQuery + key + "='" + filterFieldsAndValues.get(key) + "' and ";
                }
                //Remove "and" from generated query
                generatedQuery = generatedQuery.substring(0, generatedQuery.length() - 5);
            }

            Recordset recordSet = excelConnection.executeQuery(generatedQuery);

            List<UserObject> userObjects = populateUserObjects(recordSet);
            return userObjects;
        } catch (FilloException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<UserObject> populateUserObjects(Recordset filloRecordSet) throws Exception {
        //Uses the record set column headers to populate fields in user objects
        List<String> excelColumnHeaders = filloRecordSet.getFieldNames();
        List<UserObject> userObjects = new ArrayList<UserObject>();
        while(filloRecordSet.next()) {
            Class userClass = UserObject.class;
            UserObject userObject = (UserObject) userClass.newInstance();
            for (String columnName : excelColumnHeaders) {
                if (columnName.startsWith(SELECTOR_PREFIX)) {
                    continue;
                }
                Method fieldSetterFunction = userClass.getDeclaredMethod("set" + columnName, String.class);
                fieldSetterFunction.invoke(userObject, filloRecordSet.getField(columnName));
            }
            userObjects.add(userObject);
        }
        return userObjects;
    }

    public static void main() {

    }

}
