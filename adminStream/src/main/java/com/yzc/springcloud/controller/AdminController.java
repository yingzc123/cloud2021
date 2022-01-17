package com.yzc.springcloud.controller;


import com.yzc.springcloud.entity.ResultObject;
import com.yzc.springcloud.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author yzc
 * @since 2022-01-17
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping
    public ResultObject getAdminList(){
        return new ResultObject(200,adminService.list());
    }

}
