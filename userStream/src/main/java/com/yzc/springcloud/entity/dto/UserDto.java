package com.yzc.springcloud.entity.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class UserDto {

    @Data
    public static class UserSaveOrUpdate {
        @NotEmpty(message = "用户Id不可以为空")
        private String userId;

        @NotEmpty(message = "邮箱不可以为空")
        @Email(message = "邮箱格式不正确")
        private String email;
    }
}
