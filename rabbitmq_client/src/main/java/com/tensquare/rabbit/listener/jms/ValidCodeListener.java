package com.tensquare.rabbit.listener.jms;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.SmsUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WF on 2019-11-06 16:08
 */
@Component
@RabbitListener(queues = "sms")
public class ValidCodeListener {
    @Autowired
    private SmsUtil smsUtil;
    //监听从UserService发来的消息
    @RabbitHandler
    public void getValidCodeMessage(Map map) throws ClientException {
        //1.接收到userService发来的消息
        String mobile = (String) map.get("mobile");
        String code = (String) map.get("code");
        String templateCode = "SMS_175533606";
        Map paramMap = new HashMap();
        paramMap.put("code",code);
        //将上面的map转换为param参数
        String param = JSON.toJSONString(paramMap);

        //2.对阿里大于发送消息
        smsUtil.sendSms(mobile,templateCode,"品优购",param);
        System.out.println("消息发送成功！");
    }
}
