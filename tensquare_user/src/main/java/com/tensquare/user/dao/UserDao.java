package com.tensquare.user.dao;

import com.tensquare.user.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by WF on 2019-11-06 15:49
 */
public interface UserDao extends JpaRepository<User,String> {
    User findByMobile(String mobile);
}
