package com.yzc.springcloud.feign;

import com.yzc.springcloud.entity.ResultObject;
import com.yzc.springcloud.feign.feignHystrix.EsFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;


@Component
@FeignClient(value = "cloud-es-service",fallback = EsFeignHystrix.class)
public interface EsFeign {

    @GetMapping("/es/es_user/info")
    ResultObject getInfo();
}
