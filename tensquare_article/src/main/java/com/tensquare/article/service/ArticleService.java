package com.tensquare.article.service;

import com.tensquare.article.dao.ArticleDao;
import com.tensquare.article.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * Created by WF on 2019-11-02 16:08
 */
@Service
@Transactional
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private RedisTemplate redisTemplate;
    //1.进行文章审核
    public void examine(String articleId) {
        articleDao.examine(articleId);
    }
    //文章点赞
    public void thumbup(String articleId) {
        articleDao.thumbup(articleId);
    }
    //开始从redis中查询此对象，如果没有就查询数据库
    public Article findById(String id) {
        //1.根据id得到文章对象
        Article article = (Article) redisTemplate.opsForValue().get("article_" + id);
        //2.如果不存在，就从数据库中查询
        if(article == null){
           article = articleDao.findById(id).get();
            //3.查询成功后放到redis中(第三，四个参数代表存放的时间与单位)
            redisTemplate.opsForValue().set("article_"+id,article,10,TimeUnit.SECONDS);
        }
        //3.返回
        return article;
    }
    public void update(Article article) {
        //清空缓存
        redisTemplate.delete("article_"+article.getId());
        articleDao.save(article);
    }

    public void delete(String id) {
        //清空缓存
        redisTemplate.delete("article_"+id);
        articleDao.deleteById(id);
    }
}
