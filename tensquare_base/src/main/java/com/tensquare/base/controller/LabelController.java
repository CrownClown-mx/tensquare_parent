package com.tensquare.base.controller;


import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("label")
@CrossOrigin
public class LabelController {

    @Autowired
    private LabelService lableService;
    //1.查询所有标签
    @GetMapping
    public Result findAll(){
        //1.1查询所有的标签
        List<Label> labelList = lableService.findAll();
        //1.2返回查询成功
        return new Result(true,StatusCode.OK,"查询所有的标签成功!",labelList);

    }
    //2.根据id查询 http://localhost:9001/label/1
    @GetMapping("{labelId}")
    public Result findById(@PathVariable String labelId) {
        //2.1根据id查询标签
        Label label = lableService.findById(labelId);
        //2.2返回
        return new Result(true,StatusCode.OK,"查询单个标签成功!" ,label);
    }
    //3.添加标签
    @PostMapping
    public Result add(@RequestBody Label label){
        //3.1添加标签
        lableService.add(label);
        //3.2返回
        return new Result(true,StatusCode.OK,"添加单个标签成功");
    }
    //4.删除标签
    @DeleteMapping("{labelId}")
    public Result delete(@PathVariable String labelId){
        //删除标签
        lableService.deleteById(labelId);
        //返回
        return new Result(true,StatusCode.OK,"删除成功!");
    }
    //5.修改标签
    @PutMapping("{labelId}")
    public Result update(@RequestBody Label label,@PathVariable String labelId){
        //将修改的对象和传递过来的ID进行绑定
        label.setId(labelId);
        //修改标签
        lableService.update(label);
        //返回标签
        return new Result(true,StatusCode.OK,"修改标签成功!");
    }

}
