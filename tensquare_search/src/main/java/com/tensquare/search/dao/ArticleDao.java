package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleDao extends ElasticsearchRepository<Article,String> {
    //1.根据查询的关键字进行分页查询(自动生成要执行的rest地址，如：//http://localhost:9300/articleindex/article/_search?title=xxx&content=xxx)
    Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
