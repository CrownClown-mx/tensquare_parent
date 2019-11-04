package com.tensquare.gathering.service;

import com.tensquare.gathering.dao.GatheringDao;
import com.tensquare.gathering.pojo.Gathering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by WF on 2019-11-02 16:35
 */
@Service
@Transactional
public class GatheringService {
    @Autowired
    private GatheringDao gatheringDao;
    //将以id的值为key，以方法的返回值为值存放到缓存中
    @Cacheable(key = "#id",value="gathering")
    public Gathering findById(String id) {
        return gatheringDao.findById(id).get();
    }
    @CacheEvict(key="#id",value="gathering")
    public void deleteById(String id){
        gatheringDao.deleteById(id);
    }
    @CacheEvict(key="#gathering.id",value="gathering")
    public void update(Gathering gathering){
        gatheringDao.save(gathering);
    }
}
