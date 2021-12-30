package com.yzc.springcloud.feign.feignHystrix;

import com.yzc.springcloud.feign.UserFeign;
import org.springframework.stereotype.Component;

@Component
public class UserFeignHystrix implements UserFeign {
    @Override
    public String getOk(Integer id) {
        return "fallBack异常";
    }

    @Override
    public String getNo(Integer id) {
        return "fallBack异常";
    }

    @Override
    public String getCircuitBreaker(Integer id) {
        return "fallBackBreaker异常";
    }
}
