package com.yzc.springcloud.feign.feignHystrix;



import com.yzc.springcloud.entity.ResultObject;
import com.yzc.springcloud.feign.EsFeign;
import org.springframework.stereotype.Component;

@Component
public class EsFeignHystrix implements EsFeign {


    @Override
    public ResultObject getInfo() {
        return null;
    }
}
