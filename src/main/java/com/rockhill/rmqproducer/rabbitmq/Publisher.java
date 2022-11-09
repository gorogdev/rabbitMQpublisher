package com.rockhill.rmqproducer.rabbitmq;



import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@RestController
public class Publisher {


    @Autowired
    private RabbitTemplate template;

    @PostMapping("/publish")
    public String publishMessage(@RequestParam String symbol) {
        CustomMessage message = new CustomMessage();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        String resData = apiHelper(symbol);

        message.setSymbol(symbol);
        message.setPrice(resData);

        LocalTime localTime = LocalTime.now();
        message.setLocalTime(dtf.format(localTime));

        template.convertAndSend(Constants.EXCHANGE,
                Constants.ROUTING_KEY, message);

        return "Message Published";
    }

    private String apiHelper(String symbol){

        YahooAPIHelper yahooAPIHelper = new YahooAPIHelper();
        String resData = yahooAPIHelper.apiCall(symbol);
        return resData;
    }


}
