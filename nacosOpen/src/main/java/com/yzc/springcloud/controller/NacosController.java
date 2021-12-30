package com.yzc.springcloud.controller;

import com.yzc.springcloud.entity.ResultObject;
import com.yzc.springcloud.feign.EsFeign;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/open")
public class NacosController {

    @Autowired
   private EsFeign esFeign;

    @GetMapping
    public ResultObject get(){
        return esFeign.getInfo();
    }


    @GetMapping("/test")
    public ResultObject getTest(){
        return new ResultObject(200,"成功");
    }


}
