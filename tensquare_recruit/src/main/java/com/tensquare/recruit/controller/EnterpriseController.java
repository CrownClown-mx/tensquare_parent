package com.tensquare.recruit.controller;

import com.tensquare.recruit.pojo.Enterprise;
import com.tensquare.recruit.service.EnterpriseService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by WF on 2019-11-02 15:09
 */
@RestController
@RequestMapping("enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;
    //1.查询热门企业
    @GetMapping("search/hotlist")
    public Result findByIshot(){
        //1.1)查询热门企业
        List<Enterprise> enterprises = enterpriseService.findByIshot();
        //1.2)返回
        return new Result(true,StatusCode.OK,"查询热门企业成功！",enterprises);
    }
}
