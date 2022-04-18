package com.yzc.springcloud.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzc.springcloud.entity.ResultObject;
import com.yzc.springcloud.entity.User;
import com.yzc.springcloud.entity.dto.UserDto;
import com.yzc.springcloud.service.UserService;
import com.yzc.springcloud.utils.exceptionhandler.DiyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yzc
 * @since 2022-01-17
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/update")
    public ResultObject updateOrAddUser(@RequestBody @Valid UserDto.UserSaveOrUpdate dto){
        log.info("role/setRedis:{}", JSONObject.toJSON(dto));
        userService.updateUser(dto.getUserId(),dto.getEmail());
        return new ResultObject(200, "操作成功");
    }


    @GetMapping
    public ResultObject getTest(){
        return new ResultObject(200, "操作成功",
                userService.list());
    }

    @ResponseBody
    @PostMapping("/import")
    public ResultObject importExcelgoods(@RequestParam(value = "file") MultipartFile file, @RequestParam("type") String type) {
        String path = userService.uploadFile(file, type);
        return  new ResultObject(200, "操作成功",path);
    }

}
