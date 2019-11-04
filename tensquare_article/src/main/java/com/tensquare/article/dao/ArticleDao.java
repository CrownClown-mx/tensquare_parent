package com.tensquare.article.dao;

import com.tensquare.article.pojo.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by WF on 2019-11-02 16:09
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article> {
    //1.文章审核
    @Query(nativeQuery = true,value="update tb_article set state=1  where id=?1")
    @Modifying    //执行jpa的增、删、改操作时必须添加这个注解
    void examine(String articleId);
    //2.文章点赞
    @Query(nativeQuery = true,value="update tb_article set thumbup = thumbup+1 where id=?1")
    @Modifying
    void thumbup(String articleId);
}
