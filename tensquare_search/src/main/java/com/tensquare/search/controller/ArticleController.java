package com.tensquare.search.controller;

import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    //1.添加文章
    @PostMapping
    public Result add(@RequestBody Article article){
        //1.1)添加文章
        articleService.add(article);
        //1.2)返回
        return new Result(true,StatusCode.OK,"添加文章成功！");
    }
    //2.根据标题或内容进行搜索
    @GetMapping("search/{keywords}/{page}/{size}")
    public Result search(@PathVariable String keywords, @PathVariable int page, @PathVariable int size){
        //2.1)根据关键字进行分页查询
        PageResult<Article> articlePageResult = articleService.search(keywords,page,size);
        //2.2)返回
        return new Result(true,StatusCode.OK,"搜索成功！",articlePageResult);
    }
}
