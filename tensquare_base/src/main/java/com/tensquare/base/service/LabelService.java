package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;

@Service
public class LabelService {
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private LabelDao labelDao;

    //1.查询所有标签列表
    public List<Label> findAll() {
        return labelDao.findAll();
    }
    //2.根据id查询标签
    public Label findById(String labelId ) {
        return labelDao.findById(labelId).get();
    }
    //3.添加标签
    public void add(Label label) {
        //设置id
        long id = idWorker.nextId();
        label.setId(id + "");
        //保存标签
        labelDao.save(label);
    }
    //4.修改标签
    public void update(Label label) {
        labelDao.save(label);
    }
    //5.删除标签
    public void deleteById(String labelId) {
        labelDao.deleteById(labelId);
    }
}


