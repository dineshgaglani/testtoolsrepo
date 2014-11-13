package com.yahoo.weather.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.yahoo.weather.client.data.Rss;
import com.yahoo.weather.client.exception.InvalidInputException;

import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.JAXBContext;
import java.io.StringReader;

/**
 * Created by dgaglani on 6/2/14.
 */
public class WeatherClient {

    public static final String WEATHER_URL = "http://weather.yahooapis.com/forecastrss";
    private String locationCode;
    private Rss rssResponse;

    public void setLocationCode(String locationCode) {
        if(locationCode != "") {
            this.locationCode = locationCode;
        }
    }

    public Rss sendWeatherRequest() throws Exception{
        Client client = Client.create();
        WebResource webResource = client.resource(WEATHER_URL);
        //TO DO - Include the other query param as well
        MultivaluedMap queryParams = getQueryParams();
        String stringResponse = webResource.queryParams(queryParams).get(String.class);
        rssResponse = (Rss)JAXBContext.newInstance(Rss.class).createUnmarshaller().unmarshal(new StringReader(stringResponse));
        return rssResponse;
    }

    public MultivaluedMap getQueryParams() throws Exception {
        MultivaluedMap queryParams = new MultivaluedMapImpl();
        if(locationCode != null) {
            queryParams.add("w", locationCode);
        } else {
            throw new InvalidInputException("Location code not provided, please find the appropriate location code for your location at : https://weather.yahoo.com/");
        }
        return queryParams;
    }

}
