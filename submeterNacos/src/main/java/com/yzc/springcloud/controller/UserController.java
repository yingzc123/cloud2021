package com.yzc.springcloud.controller;


import com.yzc.springcloud.entity.User;
import com.yzc.springcloud.entity.dto.UserDto;
import com.yzc.springcloud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public void add() {
        userService.addUser();
    }


    @PostMapping("saveUser")
    public void saveUser(@RequestBody UserDto.AddUser addUser) {
        userService.saveUser(addUser.getSize(), addUser.getBatch());
    }

    @PostMapping("getUser")
    public List<User> getList(@RequestBody UserDto.SelectUser selectUser) {
        return userService.getUserList(selectUser);
    }
    @GetMapping
    public void test(){
        userService.test();
    }
}
