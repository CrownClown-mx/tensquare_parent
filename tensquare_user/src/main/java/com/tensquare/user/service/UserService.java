package com.tensquare.user.service;

import com.tensquare.user.dao.UserDao;
import com.tensquare.user.pojo.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by WF on 2019-11-06 15:46
 */
@Service
public class UserService {
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    //1.添加用户
    public void add(User user, String code) {
        //1.1)从redis中得到验证码
        String validCode = (String) redisTemplate.opsForValue().get("validCode");
        //1.2)判断是否为null
        if(validCode == null){
            throw new RuntimeException("没有保存验证码！");
        }
        if(!code.equals(validCode)){
            throw new RuntimeException("两次验证码输入不一致！");
        }
        //1.3)添加用户
        user.setId(idWorker.nextId()+"");
        user.setFollowcount(0);//关注数
        user.setFanscount(0);//粉丝数
        user.setOnline(0L);//在线时长
        user.setRegdate(new Date());//注册日期
        user.setUpdatedate(new Date());//更新日期
        user.setLastdate(new Date());//最后登陆日期
        //1.4)保存
        userDao.save(user);
    }
    //2.发送手机验证码
    public void sendCode(String mobile) {
        //2.1)定义六位数的最小值与最大值
        int min = 100000;   //六位数的最小值
        int max = 999999;   //六位数的最大值
        //2.2)产生一个[0,max-min)范围的随机数
        Random random = new Random();
        int i = random.nextInt(max - min);
        //2.3)得到产生的验证码
        String code = (i + min) + "";
        //2.4)将验证码放到redis中
        redisTemplate.opsForValue().set("validCode",code,3600,TimeUnit.SECONDS);
        //2.5)将验证码等信息发给微服务后台
        //2.5.1)构造要发送的消息内容
        Map map = new HashMap<>();
        map.put("code",code);
        map.put("mobile",mobile);
        //2.5.2)发送消息
        rabbitTemplate.convertAndSend("sms",map);
    }
}
