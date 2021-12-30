package com.yzc.springcloud.controller;


import cn.hutool.json.JSONUtil;
import com.yzc.springcloud.entity.ResultObject;
import com.yzc.springcloud.service.EmailBdService;
import com.yzc.springcloud.utils.exceptionhandler.DiyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yzc
 * @since 2021-11-05
 */
@RestController
@RequestMapping("/email_bd")
@Slf4j
public class EmailBdController {
    @Autowired
    private EmailBdService emailBdService;

    @GetMapping
    public ResultObject resultObject(String email){
        try {
            String s = emailBdService.returnSave(email);
            return new ResultObject(200,s);
        }catch (DiyException e) {
            return new ResultObject(500,e.getMsg());
        }
    }

    @GetMapping("/save_test")
    public ResultObject saveTest(){
        try {
            emailBdService.saveTest();
            return new ResultObject(200,"成功");
        }catch (DiyException e) {
            return new ResultObject(500,e.getMsg());
        }
    }

}
