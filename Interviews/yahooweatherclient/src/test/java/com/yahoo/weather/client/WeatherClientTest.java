package com.yahoo.weather.client;

import com.yahoo.weather.client.data.Rss;
import com.yahoo.weather.client.exception.InvalidInputException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.ws.rs.core.MultivaluedMap;
import java.util.Collection;


/**
 * Created by dgaglani on 6/2/14.
 */

public class WeatherClientTest {

    @DataProvider(name="positivecases")
    public Object[][] createData() {
        return new Object[][] { new Object[] { "2295020","Gurgaon, IN" }, {"2502265", "Sunnyvale, CA"}};
    }

    @DataProvider(name="nullcases")
    public Object[][] createDataNullCases() {
        return new Object[][] { new Object[] {""}, {null}};
    }

    @DataProvider(name="invalidcases")
    public Object[][] createDataInvalidCases() {
        return new Object[][] { new Object[] {"abcde"}};
    }

    @Test(dataProvider = "positivecases")
    public void testWeatherForLocation(String locationCode, String expectedLocation) throws Exception {
        WeatherClient weatherClient = createWeatherClient(locationCode);
        Rss response = weatherClient.sendWeatherRequest();
        Assert.assertTrue(response.getChannel().getDescription().contains(expectedLocation));
    }

    @Test(expectedExceptions = { InvalidInputException.class }, dataProvider = "nullcases")
    public void testWeatherForNullLocation(String locationCode) throws Exception {
        WeatherClient weatherClient = createWeatherClient(locationCode);
        weatherClient.sendWeatherRequest();
    }

    @Test(dataProvider = "invalidcases")
    public void testWeatherForInvalidLocation(String locationCode) throws Exception {
        WeatherClient weatherClient = createWeatherClient(locationCode);
        Rss response = weatherClient.sendWeatherRequest();
        Assert.assertTrue(response.getChannel().getItem().getTitle().equals("City not found"));
    }

    @Test(dataProvider = "positivecases")
    public void testQueryParams(String locationCode, String expectedLocation) throws Exception{
        WeatherClient weatherClient = createWeatherClient(locationCode);
        MultivaluedMap queryParamsMap = weatherClient.getQueryParams();
        Assert.assertTrue(queryParamsMap.get("w").toString().contains(locationCode));
    }

    private WeatherClient createWeatherClient(String locationCode) {
        WeatherClient weatherClient = new WeatherClient();
        weatherClient.setLocationCode(locationCode);
        return weatherClient;
    }

}
