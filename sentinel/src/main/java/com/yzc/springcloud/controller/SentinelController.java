package com.yzc.springcloud.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sentinel")
@Slf4j
public class SentinelController {




    @GetMapping("/hello")
    public String resultObject(){
        return "hello word";
    }


    @GetMapping("/yzc")
    public String yzc(){
        return "yzc";
    }

    @GetMapping("/xh")
    public String xh() throws Exception {
        int i = 10 / 0;
        return "xh";
    }
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "tole" ,fallback ="tole" )
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,@RequestParam(value = "p2",required = false) String p2){
      int age=10/0;
        return "成功";
    }

    public String tole(String p1, String p2, BlockException blockException){

        return "/(ㄒoㄒ)/~~";
    }
}
