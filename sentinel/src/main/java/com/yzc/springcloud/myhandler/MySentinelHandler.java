package com.yzc.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yzc.springcloud.entity.ResultObject;

public class MySentinelHandler {

    
    public static ResultObject resultObject(BlockException blockException) {
        return new ResultObject(444, "全局执行blockException/(ㄒoㄒ)/~~");
    }


    public static ResultObject resultObject2(BlockException blockException) {
        return new ResultObject(444, "全局执行2blockException/(ㄒoㄒ)/~~");
    }


}
