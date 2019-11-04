package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by WF on 2019-11-04 15:24
 */
public interface SpitDao extends MongoRepository<Spit,String> {
    //1.根据父id查询上级吐槽列表
    Page<Spit> findByParentid(String parentid, Pageable pageable);
}
