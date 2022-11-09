package com.rockhill.rmqproducer.rabbitmq;



import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
public class Publisher {


    @Autowired
    private RabbitTemplate template;

    @PostMapping("/publish")
    public String publishMessage(@RequestParam String symbol) {
        CustomMessage message = new CustomMessage();
        message.setSymbol(symbol);
        template.convertAndSend(Constants.EXCHANGE,
                Constants.ROUTING_KEY, message);

        return "Message Published";
    }


}
