package com.yzc.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.yzc.springcloud.entity.ResultObject;
import com.yzc.springcloud.feign.SentinelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/feign")
@Slf4j
public class FeignController {

    @Resource
    private SentinelService sentinelService;

    @GetMapping("/getFeign")
    @SentinelResource(value = "getFeign")
    public ResultObject getFeign(){
        return sentinelService.getDemo();
    }
}
