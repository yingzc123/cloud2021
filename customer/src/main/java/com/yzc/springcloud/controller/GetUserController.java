package com.yzc.springcloud.controller;

import com.yzc.springcloud.feign.UserFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/getFeignUser")
@Slf4j
public class GetUserController {


    @Resource
    private UserFeign userFeign;
    @GetMapping("/ok/{id}")
    public String get(@PathVariable("id") Integer id){
        String ok = userFeign.getOk(id);
        log.info("return:",ok);
        return ok;
    }


    @GetMapping("/no/{id}")
    public String getNo(@PathVariable("id") Integer id){
        String no = userFeign.getNo(id);
        log.info("return:",no);
        return no;
    }

    @GetMapping("/breaker/{id}")
    public String getCircuitBreaker(@PathVariable("id") Integer id){
        String no = userFeign.getCircuitBreaker(id);
        log.info("return:",no);
        return no;
    }

}
