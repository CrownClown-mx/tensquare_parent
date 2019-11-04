package com.tensquare.article.controller;

import com.tensquare.article.service.ArticleService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by WF on 2019-11-02 16:07
 */
@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    //1.文章审核
    @PutMapping("examine/{articleId}")
    public Result examine(@PathVariable String articleId){
        //1.1)进行文章审核
        articleService.examine(articleId);
        //1.2)返回
        return new Result(true,StatusCode.OK,"审核成功！");
    }
    //2.文章点赞
    @PutMapping("thumbup/{articleId}")
    public Result thumbup(@PathVariable String articleId){
        //2.1)文章点赞
        articleService.thumbup(articleId);
        //2.2)返回
        return new Result(true,StatusCode.OK,"点赞成功！");
    }
}
