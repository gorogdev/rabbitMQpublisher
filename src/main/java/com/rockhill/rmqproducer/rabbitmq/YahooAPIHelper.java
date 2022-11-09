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

        String temp = res.toString();
        int t = temp.indexOf("raw");
        String price = temp.substring(t+4, temp.indexOf(',', t+4));

        return price;
    }


}
