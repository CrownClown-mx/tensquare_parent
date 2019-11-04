package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.EnterpriseDao;
import com.tensquare.recruit.pojo.Enterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WF on 2019-11-02 15:11
 */
@Service
public class EnterpriseService {
    @Autowired
    private EnterpriseDao enterpriseDao;
    //1.查询热门企业
    public List<Enterprise> findByIshot() {
        return enterpriseDao.findByIshot("1");
    }
}
