package com.tensquare.user.controller;

import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UserController {
    @Autowired
    UserService userService;
    //1.用户注册
    @PostMapping("register/{code}")
    public Result add(@RequestBody User user, @PathVariable String code){
        //添加用户
        userService.add(user,code);
        //返回
        return new Result(true,StatusCode.OK,"用户注册成功");
    }
    //2.发送手机验证码
    @PostMapping("sendsms/{mobile}")
    public Result sendCode(@PathVariable String mobile){
        //发送手机验证码
        userService.sendCode(mobile);
        //返回
        return new Result(true,StatusCode.OK,"验证码发送成功!!!");
    }
}
