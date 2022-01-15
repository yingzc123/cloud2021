package com.yzc.springcloud.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RoleDto {

    @Data
    public static class RoleByRedis {
        @NotEmpty(message = "key不可以为空")
        private String key;
        @NotEmpty(message = "value不能为空")
        private String value;
    }
}
