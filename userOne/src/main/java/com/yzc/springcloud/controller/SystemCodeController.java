package com.yzc.springcloud.controller;


import cn.hutool.core.lang.UUID;
import com.yzc.springcloud.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yzc
 * @since 2021-09-02
 */
@RestController
@RequestMapping("/system_code")
public class SystemCodeController {

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/test_redis")
public void saveRedis(){
        UUID uuid = UUID.randomUUID();
        redisUtil.lSet(uuid.toString().substring(0,4),uuid,10000);
    }
 }
