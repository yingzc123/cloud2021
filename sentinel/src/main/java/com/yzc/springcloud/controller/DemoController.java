package com.yzc.springcloud.controller;

import com.yzc.springcloud.myhandler.MySentinelHandler;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.yzc.springcloud.entity.ResultObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sentinel")
@Slf4j
public class DemoController {



    @GetMapping("/get")
    @SentinelResource(value = "get")
    public ResultObject get(){
        return new ResultObject(200,"成功");
    }

    @GetMapping("/getDemo")
    @SentinelResource(value = "getDemo",
            blockHandlerClass = MySentinelHandler.class,
            blockHandler = "resultObject2")
    public ResultObject getDemo(){
        return new ResultObject(200,"我是你爸爸");
    }
}
