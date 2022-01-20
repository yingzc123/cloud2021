package com.yzc.springcloud.entity.vo;


import com.alibaba.excel.annotation.ExcelProperty;
import com.yzc.springcloud.annotation.VoTitle;
import lombok.Data;


public class RoleVO {
    @Data
    public static class RoleReturnVO {
        @ExcelProperty(value = "编号", index = 0)
        private Integer roleId;
        @ExcelProperty(value = "身份", index = 1)
        @VoTitle(value = "身份", sort = 1)
        private String roleName;
        @ExcelProperty(value = "状态", index = 2)
        @VoTitle(value = "状态", sort = 2)
        private String state;

        public String getState() {
            if ("1".equals(state)) {
                return "正常";
            }
            return "异常";
        }
    }
}
