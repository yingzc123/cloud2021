package com.yzc.springcloud.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RoleDto {

    @Data
    public static class RoleByRedis {
        @NotEmpty(message = "key不可以为空")
        private String key;
        @NotEmpty(message = "value不能为空")
        private String value;
        @NotNull(message = "过期时间不能为空")
        @Size(min = 1, max = 3, message = "过期时间长度必须是1-3个字符")
        private Integer time;


    }
}
