package com.tensquare.rabbit.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by WF on 2019-11-06 15:12
 */
@RabbitListener(queues = "zl_shenzhen")
@Component
public class ShenZhenListener {
    @RabbitHandler
    public void getMessage(String message){
        System.out.println("zl_shenzhen校区接收到消息：" + message);
    }
}
