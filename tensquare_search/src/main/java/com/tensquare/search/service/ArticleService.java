package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleDao;
import com.tensquare.search.pojo.Article;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import util.IdWorker;


@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private IdWorker idWorker;
    //1.添加文章
    public void add(Article article) {
        //1. 设置id字段
        article.setId(idWorker.nextId()+"");
        //2.保存
        articleDao.save(article);
    }
    //2.根据关键字进行分页查询
    public PageResult<Article> search(String keywords, int page, int size) {
        //2.1)得到一个分页对象
        Page<Article> articlePage = articleDao.findByTitleOrContentLike(keywords, keywords, PageRequest.of(page - 1, size));
        //2.2)返回PageResult
        return new PageResult<>(articlePage.getTotalElements(),articlePage.getContent());
    }
}
