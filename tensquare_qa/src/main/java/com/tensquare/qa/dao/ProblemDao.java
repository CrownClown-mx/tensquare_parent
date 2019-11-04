package com.tensquare.qa.dao;


import com.tensquare.qa.pojo.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by WF on 2019-11-02 15:41
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem> {

    //1.查询最新问题列表
    @Query(nativeQuery = true,value="select * from tb_problem pb,tb_pl where id=problemid and labelid=?1 order by createtime desc")
    Page<Problem> findProblemsByLabelid(String labelId, Pageable pageable);

    //2.查询热门问题
    @Query(nativeQuery = true,value="select * from tb_problem pb,tb_pl where id=problemid and labelid=?1 order by reply desc")
    Page<Problem> findHotListByLabelId(String labelId,Pageable pageable);
}
