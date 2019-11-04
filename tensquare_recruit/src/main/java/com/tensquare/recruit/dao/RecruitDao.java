package com.tensquare.recruit.dao;

import com.tensquare.recruit.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by WF on 2019-11-02 15:23
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit> {
    //1.查询最新的推荐职位
    List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String state);
    //2.查询最新职位
    List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String state);
}
