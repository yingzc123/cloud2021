package com.yzc.springcloud.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yzc.springcloud.entity.User;
import com.yzc.springcloud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public void add(){
        userService.addUser();
    }

    @GetMapping
    public IPage<User> getList(Integer index, Integer size){
      return   userService.getUserList(index,size);
    }
}
