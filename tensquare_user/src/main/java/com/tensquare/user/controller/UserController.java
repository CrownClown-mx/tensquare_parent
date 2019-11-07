package com.tensquare.user.controller;

import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import net.bytebuddy.asm.Advice;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by WF on 2019-11-06 15:43
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private HttpServletRequest request;
    //1.用户注册
    @PostMapping("register/{code}")
    public Result add(@RequestBody User user, @PathVariable String code){
        //1.1)添加用户
        userService.add(user,code);
        //1.2)返回
        return new Result(true,StatusCode.OK,"注册用户成功！");
    }
    //2.发送手机验证码
    @PostMapping("sendsms/{mobile}")
    public Result sendCode(@PathVariable String mobile){
        //2.1)发送手机验证码
        userService.sendCode(mobile);
        //2.2)返回
        return new Result(true,StatusCode.OK,"发送验证码成功！");
    }
    //3.添加用户
    @PostMapping
    public Result add(@RequestBody User user) {
        //添加用户
        userService.add(user);
        return new Result(true,StatusCode.OK,"添加用户成功");
    }
    //4.用户登录
    @PostMapping("login")
    public Result login(@RequestBody User user){
        //4.1)用户登录
        User u = userService.login(user);
        //4.2)返回
        if(u != null) {
            //4.2.1)创建token信息
            String token = jwtUtil.createJwt(u.getId(), u.getNickname(), "user");
            //4.2.2)创建map，为了便于在后面获取这个token信息
            Map map = new HashMap<>();
            map.put("name",u.getNickname());
            map.put("token",token);

            return new Result(true, StatusCode.OK, "用户登录成功！",map);
        }
        return new Result(false,StatusCode.ERROR,"用户登录失败！");
    }
    //5.删除用户
    @DeleteMapping("{userId}")
    public Result deleteById(@PathVariable String userId){
        //5.1)得到claims对象
        Claims claims = (Claims) request.getAttribute("claims");
        //5.2)判断是否为null，再取出角色信息
        if(claims != null){
            //5.3)得到角色信息
            String roles = (String) claims.get("roles");
            //5.4)判断角色是否是管理员
            if(StringUtils.isNotBlank(roles)){
                if("admin".equals(roles)){
                    userService.deleteById(userId);
                    return new Result(true,StatusCode.OK,"删除用户成功！");
                }
            }
        }
        return new Result(false,StatusCode.ACCESSERROR,"无权删除用户！");
    }
}
