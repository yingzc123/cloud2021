package com.yzc.springcloud.feign;

import com.yzc.springcloud.feign.feignImpi.SentinelServiceImpl;
import com.yzc.springcloud.entity.ResultObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
@FeignClient(value = "cloudalibaba-sentinel-service",fallback = SentinelServiceImpl.class)
public interface SentinelService {
    @GetMapping("/sentinel/getDemo")
     ResultObject getDemo();
}
