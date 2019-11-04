package com.tensquare.recruit.controller;

import com.tensquare.recruit.pojo.Recruit;
import com.tensquare.recruit.service.RecruitService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by WF on 2019-11-02 15:20
 */
@RestController
@RequestMapping("recruit")
public class RecruitController {
    @Autowired
    private RecruitService recruitService;
    //1.推荐职位查询
    @GetMapping("search/recommend")
    public Result recommend(){
        //1.1)查询推荐职位
        List<Recruit> recruits =  recruitService.recommend();
        //1.2)返回
        return new Result(true,StatusCode.OK,"查询推荐职位成功！",recruits);
    }
    //2.查询最新职位
    @GetMapping("search/newlist")
    public Result newlist(){
        //2.1)查询最新职位
        List<Recruit> recruits = recruitService.newlist();
        //2.2)返回
        return new Result(true,StatusCode.OK,"查询最新职位成功！",recruits);
    }
}
