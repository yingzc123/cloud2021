package com.yzc.springcloud.controller;


import com.yzc.springcloud.entity.ResultObject;
import com.yzc.springcloud.entity.User;
import com.yzc.springcloud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yzc
 * @since 2021-11-05
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;

    @GetMapping
    public ResultObject resultObject(){
        return new ResultObject(200,"成功",accessKeyId);
    }

    @PostMapping
    public ResultObject updateUser()  {
        try {
            userService.updateUser();
            return new ResultObject(200,"成功");
        }catch (Exception e) {
            return new ResultObject(500,e.getMessage());
        }
    }

}
