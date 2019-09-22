package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;

/**
 * Created by Dinesh on 9/21/2019.
 */
public class StockPriceClient {

    public RequestSpecBuilder getBaseRequest() {
//        "326f3dcc68msh19e51dc3cab32eap128c10jsn1edad8b2f397" -- TODO delete
        String API_KEY = System.getenv("API_KEY");
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder
//                .setBaseUri("http://localhost:8000")
                .setBaseUri("https://investors-exchange-iex-trading.p.rapidapi.com/stock")
                .addHeader("x-rapidapi-host", "investors-exchange-iex-trading.p.rapidapi.com")
                .addHeader("x-rapidapi-key", API_KEY);

        return builder;
    }

    public Float getHighForStock(String stockName) {
       return RestAssured.given().spec(getBaseRequest().build()).get(stockName + "/ohlc").jsonPath().getFloat("high");
    }


}
