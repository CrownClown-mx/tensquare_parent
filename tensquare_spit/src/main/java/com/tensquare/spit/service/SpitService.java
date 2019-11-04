package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import util.IdWorker;
import java.util.List;
@Service
public class SpitService {
    @Autowired
    private SpitDao spitDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private MongoTemplate mongoTemplate;

    //1.查询所有
    public List<Spit> findAll() {
        return spitDao.findAll();
    }
    //2.查询单个吐槽
    public Spit findById(String spiltId) {
        return spitDao.findById(spiltId).get();
    }
    //3.添加单个吐槽
    public  void add(Spit spit) {
        long id = idWorker.nextId();
        spit.set_id(id+"");
        spitDao.save(spit);
    }
    //4.修改吐槽
    public void update(Spit spit) {spitDao.save(spit);}
    //5.删除吐槽
    public void delete(String spitId) {spitDao.deleteById(spitId);}
    //6.根据父id查询上级吐槽列表
    public PageResult<Spit> findSpitByParentId(String parentid, int page, int size) {
        //6.1查询上级吐槽列表
        Page<Spit> spitPage = spitDao.findByParentid(parentid, PageRequest.of(page - 1, size));
        //6.2返回结果
        return new PageResult<>(spitPage.getTotalElements(),spitPage.getContent());
    }
    //7.点赞(方法一: 传统方法)
    public void thumbup(String spitId) {
        //根据点赞id 查询出点赞对象
        Spit spit = spitDao.findById(spitId).get();
        //修改点赞数
        spit.setThumbup(spit.getThumbup()+1);
        //保存修改
        spitDao.save(spit);
    }
    //8.点赞(方法二:使用mongoTemplate模板的方法)
    public void thumbup2(String spitId) {
        //构造查询对象
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitId));
        //构成要修改的对象(即将你要修改的一系列参数封装成对象)
        Update update = new Update();
        update.inc("thumbup",1);  //参数一:代表要修改的字段 参数二:修改的值
        //进行点赞数修改
        mongoTemplate.updateFirst(query,update,"spit");
        //参数1:查询参数 参数2：修改对象的参数 参数3：集合名（表名）
    }
}
