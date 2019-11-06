package com.tensquare.rabbit.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by WF on 2019-11-06 15:25
 */
@Component
@RabbitListener(queues = "sanguoyanyi")
public class SanGuoYanYiListener {
    @RabbitHandler
    public void getMessage(String message){
        System.out.println("sanguoyanyi正在接收消息：" + message);
    }
}
