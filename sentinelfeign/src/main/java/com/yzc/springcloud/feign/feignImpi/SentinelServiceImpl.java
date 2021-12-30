package com.yzc.springcloud.feign.feignImpi;

import com.yzc.springcloud.entity.ResultObject;
import com.yzc.springcloud.feign.SentinelService;
import org.springframework.stereotype.Component;

@Component
public class SentinelServiceImpl  implements SentinelService {
    @Override
    public ResultObject getDemo() {
        return new ResultObject(4444,"调用服务出现异常，服务降级");
    }
}
