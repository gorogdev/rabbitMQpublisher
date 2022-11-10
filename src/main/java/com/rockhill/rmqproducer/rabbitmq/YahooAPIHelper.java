package com.rockhill.rmqproducer.rabbitmq;


import org.springframework.web.client.RestTemplate;



public class YahooAPIHelper {

    public String apiCall(String symbol){
        RestTemplate restTemplate = new RestTemplate();
        Object res = restTemplate.getForObject(getUrl(symbol), Object.class);
        return getPriceFromResObj(res);
    }

    private static String getUrl(String symbol) {
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.BASE_URL).append(symbol).append(Constants.QP);
        return sb.toString();
    }

    private static String getPriceFromResObj(Object res) {

        /*
        Note: the get response from the Yahoo API
        is a pretty large JSON.  A more correct way to use
        it would be to write an entity class and
        access the fields accordingly.  But since 1) this
        example microservice won't be altered and 2) the Yahoo
        API response won't likely change, I simply
        grabbed the raw price via String methods below.
         */


        String temp = res.toString();
        int t = temp.indexOf(Constants.RWKEY);
        String price = temp.substring(t+4, temp.indexOf(',', t+4));
        return price;
    }


}
