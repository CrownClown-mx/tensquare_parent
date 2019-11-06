package com.tensquare.rabbit.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by WF on 2019-11-06 15:02
 */
@Component
@RabbitListener(queues = "zelin")
public class RabbitmqListener {
    @RabbitHandler
    public void getMessage(String message){
        System.out.println(message);
    }
}
