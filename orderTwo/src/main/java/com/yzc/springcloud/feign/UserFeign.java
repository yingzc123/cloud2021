package com.yzc.springcloud.feign;

import com.yzc.springcloud.entity.feignVo.User;
import com.yzc.springcloud.feign.feignHystrix.UserFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "cloud-user-service",fallback = UserFeignHystrix.class)
public interface UserFeign {

    @GetMapping("/user/user/byUserId/{userId}")
    User getUserId(@PathVariable("userId") String userId);
}
