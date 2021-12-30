package com.yzc.springcloud.controller;

import com.yzc.springcloud.entity.ResultObject;
import com.yzc.springcloud.entity.User;
import com.yzc.springcloud.entity.esQuery.UserQuery;
import com.yzc.springcloud.service.UserEsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/es_user")
@RefreshScope
public class UserController {
    @Autowired
    private UserEsService userEsService;

    @Value("${yzc.info}")
    private String testValue;

    @GetMapping("/info")
    public ResultObject getInfo(){
        return new ResultObject(200,"请求成功",testValue);
    }

    @GetMapping
    public ResultObject getList(){
        return new ResultObject(200,"查询成功", userEsService.getListUser());
    }

    @GetMapping("/es_page")
    public ResultObject  getPage(@RequestParam(value = "index" ,defaultValue = "0") Integer index,
                                 @RequestParam(value = "size" ,defaultValue = "20") Integer size,
                                 @RequestParam(value = "name" ,required = false)String name,
                                 @RequestParam(value = "tel" ,required = false)String tel,
                                 @RequestParam(value = "sex" ,required = false)String sex,
                                 @RequestParam(value = "age" ,required = false)Integer age){

            UserQuery userQuery = new UserQuery();
            userQuery.byName(name).byTel(tel).bySex(sex).gtAge(age);
            Page<User> page = userEsService.getPage(userQuery, index, size);
            return new ResultObject(200,"查询成功",page);
    }

    @PostMapping
    public ResultObject  save(){
        userEsService.save();
        return new ResultObject(200,"保存成功");
    }

    @GetMapping("/test")
    public void  testException(){
        userEsService.testException();
    }

}
