package com.yzc.springcloud.entity.vo;

import com.yzc.springcloud.annotation.VoTitle;
import lombok.Data;

public class RoleVO {
    @Data
    public static class RoleReturnVO{
        @VoTitle(value = "身份",sort = 1)
        String roleName;
        @VoTitle(value = "状态",sort = 2)
        String state;
        public String getState(){
            if("1".equals(state)) {
                return "正常";
            }
            return "异常";
        }
    }
}
