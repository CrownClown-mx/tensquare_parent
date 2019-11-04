package com.tensquare.recruit.dao;

import com.tensquare.recruit.pojo.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by WF on 2019-11-02 15:13
 */
public interface EnterpriseDao extends JpaRepository<Enterprise,String>,JpaSpecificationExecutor<Enterprise> {

    //1.查询热门企事业
    //按照这种指定的规则定义方法，spingjpa会自动生在sql语句
    List<Enterprise> findByIshot(String ishot);
}
