package com.test.demo.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dgaglani on 9/14/14.
 */
public class DBOperator {

    public static String writeToDb(DBProvider dbProvider, String contentToWrite, String key) throws Exception {
        //Post calls this
        String dbFileName = dbProvider.provideDb();
        Map<String, String> existingRecordsMap = new HashMap<String, String>();
        existingRecordsMap = CrudServiceUtils.getFileContentsAsKeyValueMap(dbFileName);
        String newKey = key;
        if (newKey == null) {
            //Generate a unique key
            newKey = generateRandomKey(existingRecordsMap);
        }
        //Update this key. This will both overwrite if key exists or if key does not exist, it will write as the provided key itself
        existingRecordsMap.put(newKey, contentToWrite);
        CrudServiceUtils.writeToDbFile(existingRecordsMap, dbFileName);
        return newKey;
    }

    private static String generateRandomKey(Map<String, String> existingRecordsMap) {
        SecureRandom random = new SecureRandom();
        String randomKey = new BigInteger(130, random).toString(32);
        if (existingRecordsMap.keySet().contains(randomKey)) {
            randomKey = generateRandomKey(existingRecordsMap);
        }
        return randomKey;
    }

    public static String readFromDb(DBProvider dbProvider, String key) throws Exception {
        //Get calls this
        String dbFileName = dbProvider.provideDb();
        Map<String, String> existingRecordsMap = new HashMap<String, String>();
        existingRecordsMap = CrudServiceUtils.getFileContentsAsKeyValueMap(dbFileName);
        if (existingRecordsMap.containsKey(key)) {
            return existingRecordsMap.get(key);
        } else {
            return "";
        }
    }

    public static void removeFromDb(DBProvider dbProvider, String key) throws Exception {
        //Delete calls this
        String dbFileName = dbProvider.provideDb();
        Map<String, String> existingRecordsMap = new HashMap<String, String>();
        existingRecordsMap = CrudServiceUtils.getFileContentsAsKeyValueMap(dbFileName);
        if (existingRecordsMap.containsKey(key)) {
            existingRecordsMap.remove(key);
        }
        //Save properties back
        CrudServiceUtils.writeToDbFile(existingRecordsMap, dbFileName);

    }
}
