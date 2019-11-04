package com.tensquare.qa.service;

import com.tensquare.qa.dao.ProblemDao;
import com.tensquare.qa.pojo.Problem;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WF on 2019-11-02 15:40
 */
@Service
public class ProblemService {

    @Autowired
    private ProblemDao problemDao;
    //1.查询最新问题
    public PageResult<Problem> newlist(String label, int page, int size) {
        //1.1)查询得到最新问题
        Page<Problem> problemPage = problemDao.findProblemsByLabelid(label, PageRequest.of(page - 1, size));
        //1.2)将上面的分页对象转换为PageResult对象
        return new PageResult<>(problemPage.getTotalElements(),problemPage.getContent());
    }
    //2.查询热门问题
    public PageResult<Problem> hotlist(String label, int page, int size) {
        //2.1)查询热门问题
        Page<Problem> problemPage = problemDao.findHotListByLabelId(label, PageRequest.of(page - 1, size));
        //2.2)转换对象为PageResult
        return new PageResult<>(problemPage.getTotalElements(),problemPage.getContent());
    }
}
