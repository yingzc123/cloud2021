package com.yzc.springcloud.controller;



import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzc.springcloud.entity.ResultObject;
import com.yzc.springcloud.entity.User;
import com.yzc.springcloud.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Set;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yzc
 * @since 2021-08-08
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/byUserId/{userId}")
    public User getUserId(@PathVariable("userId") String userId){
        return userService.getById(userId);
    }


    @GetMapping
    public ResultObject getPageUser(@RequestParam(value = "index" ,defaultValue = "1") Integer index,
                                    @RequestParam(value = "size" ,defaultValue = "20") Integer size,
                                    @RequestParam(value = "user_id" ,required = false)String userId,
                                    @RequestParam(value = "user_name" ,required = false)String userName,
                                    @RequestParam(value = "age" ,required = false)Integer age,
                                    @RequestParam(value = "ids" ,required = false) Set<String> userIds){
        QueryWrapper<User> wq = new QueryWrapper<>();
        if(StrUtil.isNotEmpty(userId)) {
            wq.eq("user_id",userId);
        }
        if(StrUtil.isNotEmpty(userName)) {
            wq.eq("user_name",userName);
        }
        if(null != age) {
            wq.eq("age",age);
        }
        if(CollectionUtil.isNotEmpty(userIds)) {
            wq.in("user_id",userIds);
        }
        Page<User> page = userService.page(new Page<>(index, size), wq);
        return new ResultObject(200,"查询成功",page);
    }
    @GetMapping("/getRedis/{key}")
    public String getRedis(@PathVariable("key") String key){
        String redis = userService.getRedis(key);
        return redis;
    }

   @DeleteMapping("/delete")
    public void delete(String id){
       System.out.println("111");
       System.out.println(id);
       userService.removeById(id);
   }

    @GetMapping("/get")
    public ResultObject get(){
        try {
            userService.checkIsMsg();
           return new ResultObject(200,"") ;
        }catch (Exception e) {
            return new ResultObject(500,e.getMessage()) ;
        }

    }

}
