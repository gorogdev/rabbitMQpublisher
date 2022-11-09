package com.rockhill.rmqproducer.rabbitmq;


import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.json.JsonParser;
import org.springframework.core.io.UrlResource;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

public class YahooAPIHelper {


    String url = "https://query1.finance.yahoo.com/v10/finance/quoteSummary/aapl?modules=financialData";

    public String apiCall(String symbol){
        RestTemplate restTemplate = new RestTemplate();
        Object res = restTemplate.getForObject(url, Object.class);

        return getPriceFromResObj(res);
    }

    private static String getPriceFromResObj(Object res) {

        /*
        Note: the get response from the Yahoo API
        is a pretty large JSON.  A more correct way to use
        it would be to write an entity class and
        access the fields accordingly.  But since 1) this
        example microservice won't be altered and 2) the API
        response won't likely change, I simply
        grabbed the raw price via substring methods below.
         */


        String temp = res.toString();
        int t = temp.indexOf("raw");
        String price = temp.substring(t+4, temp.indexOf(',', t+4));
        return price;
    }


}
