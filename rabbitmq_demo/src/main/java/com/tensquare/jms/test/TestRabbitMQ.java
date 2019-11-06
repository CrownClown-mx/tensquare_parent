package com.tensquare.jms.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRabbitMQ {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    //用direct发送消息
    @Test
    public void sendMessage() {
        rabbitTemplate.convertAndSend("晴明","大家好,我是晴明大佬");
    }
    //用fanout发送消息,只用给exchange发送消息即可
    @Test
    public void sendFanoutMessage(){
        rabbitTemplate.convertAndSend("zl-school","","晴明大佬正在使用fanout发送");
    }
    //使用topic发送消息,只有在routingkey中定义了感兴趣的queue才能收到消息
    @Test
    public void sendTopicMessage1(){
        //以goods开头的消息
        rabbitTemplate.convertAndSend("fourbooks","goods.aa","晴明大佬正在发送goods.aa");
    }
    @Test
    public void  sendTopicMessage2(){
        //以log结尾的消息
        rabbitTemplate.convertAndSend("fourbooks","aa.log","正在发送aa.log这种消息");
    }
    @Test
    public void sendTopicMessage3(){
        //发送的是good.log的消息
        rabbitTemplate.convertAndSend("fourbooks","goods.log","正在发送goods.log这种消息");
    }

}
