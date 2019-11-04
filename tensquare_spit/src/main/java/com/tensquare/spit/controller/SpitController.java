package com.tensquare.spit.controller;


import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("spit")
public class SpitController {

    @Autowired
    private SpitService spitService;
    @Autowired
    private RedisTemplate redisTemplate;

    //1.查询所有吐槽
    @GetMapping
    public Result findAll(){
        //1.1查询出所有的吐槽信息
        List<Spit> spits = spitService.findAll();
        //返回
        return new Result(true,StatusCode.OK,"添加吐槽成功",spits);


    }
    //2.根据id查询
    @GetMapping("{spitId}")
    public Result findById(@PathVariable String spitId) {
        Spit spit = spitService.findById(spitId);
        return new Result(true,StatusCode.OK,"查询单个吐槽成功!",spit);
    }
    //3.添加吐槽
    @PostMapping
    public Result add(@RequestBody Spit spit){
        spitService.add(spit);
        return new Result(true,StatusCode.OK,"添加单个吐槽成功!");
    }
    //4.修改吐槽
    @PutMapping("{spitId}")
    public Result update(@RequestBody Spit spit,@PathVariable String spitId){
        //将id与对象绑定
        spit.set_id(spitId);
        //执行修改操作
        spitService.update(spit);
        //返回
        return new Result(true,StatusCode.OK,"修改吐槽成功!");
    }
    //5.删除吐槽
    @DeleteMapping("{spitId}")
    public Result delete(@PathVariable String spitId){
        spitService.delete(spitId);
        return new Result(true,StatusCode.OK,"删除吐槽成功!");
    }
    //6.根据上级吐槽id查询吐槽列表
    @GetMapping("comment/{parentid}/{page}/{size}")
    public Result findSpitByParentId(@PathVariable String parentid,@PathVariable int page,@PathVariable int size){
        PageResult<Spit> spitPageResult = spitService.findSpitByParentId(parentid,page,size);
        return new Result(true,StatusCode.OK,"查询上级吐槽id查询吐槽列表成功!",spitPageResult);
    }
    //7.吐槽点赞
    @PutMapping("thumbup/{spitId}")
    public Result thumbup(@PathVariable String spitId){
        //7.1）避免重复点赞思路：在redis中记载一个标志，当点赞时，就确定是否有此标志，就有不能点赞！！！
        String userid = "1024";
        String flag = (String) redisTemplate.opsForValue().get("thumbup_" + userid + "_" + spitId);
        //7.2)判断是否在redis中有此内容
        if(StringUtils.isNotBlank(flag)){
            return new Result(false,StatusCode.REPERROR,"对不起，不能重复点赞！");
        }
        //7.3)开始点赞
        spitService.thumbup2(spitId);
        //7.4)将标志信息放到redis中
        redisTemplate.opsForValue().set("thumbup_" + userid + "_" + spitId,"aaaa");
        //7.5)返回
        return new Result(true,StatusCode.OK,"点赞成功！");
    }
}
