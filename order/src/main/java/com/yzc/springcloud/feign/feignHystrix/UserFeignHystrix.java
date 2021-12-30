package com.yzc.springcloud.feign.feignHystrix;

import com.yzc.springcloud.entity.feignVo.User;
import com.yzc.springcloud.feign.UserFeign;
import org.springframework.stereotype.Component;

@Component
public class UserFeignHystrix implements UserFeign {

    @Override
    public User getUserId(String userId) {
        return null;
    }
}
