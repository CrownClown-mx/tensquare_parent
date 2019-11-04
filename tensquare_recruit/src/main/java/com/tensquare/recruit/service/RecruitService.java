package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.RecruitDao;
import com.tensquare.recruit.pojo.Recruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WF on 2019-11-02 15:22
 */
@Service
public class RecruitService {

    @Autowired
    private RecruitDao recruitDao;
    //1. 查询推荐职位
    public List<Recruit> recommend() {
        return recruitDao.findTop4ByStateOrderByCreatetimeDesc("2");
    }
    //2.查询最新职位
    public List<Recruit> newlist() {
        return recruitDao.findTop12ByStateNotOrderByCreatetimeDesc("0");
    }
}
