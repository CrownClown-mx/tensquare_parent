package com.tensquare.qa.controller;

import com.tensquare.qa.pojo.Problem;
import com.tensquare.qa.service.ProblemService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.apache.catalina.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by WF on 2019-11-02 15:38
 */
@RestController
@RequestMapping("problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;
    //1.查询最新的问题列表
    @GetMapping("newlist/{label}/{page}/{size}")
    public Result newlist(@PathVariable String label, @PathVariable int page, @PathVariable int size){
        //1.1)查询最新问题
        PageResult<Problem> problems = problemService.newlist(label,page,size);
        //1.2)返回
        return new Result(true,StatusCode.OK,"查询最新问题成功！",problems);
    }
    //2.查询热门问答列表
    @GetMapping("hotlist/{label}/{page}/{size}")
    public Result hotlist(@PathVariable String label,@PathVariable int page,@PathVariable int size){
        //2.1)查询热门问答列表
        PageResult<Problem> hotlist = problemService.hotlist(label,page,size);
        //2.2)返回
        return new Result(true,StatusCode.OK,"查询热门问答成功!",hotlist);
    }
}
