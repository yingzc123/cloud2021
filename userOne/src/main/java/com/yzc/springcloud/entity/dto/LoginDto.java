package com.yzc.springcloud.entity.dto;

import lombok.Data;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LoginDto {
    @Data
    public static class Login{
        @NotNull(message = "userNum不能为空")
        private String userNum;
        private String  ip;
    }

    @Data
    public static class MsgAll{
        @NotEmpty(message = "发送账号不能为空")
        private String carrier;

        //1==手机 2== 邮件
        @NotNull(message = "类型不能为空")
        private Integer type;

        @NotEmpty(message = "验证码不能为空")
        private String code;

        //MsgEnum.MsgAll
        @NotEmpty(message = "发送场景不能为空")
        private String sceneType;
    }


    @Data
    public static class SendMsgCarrier{
        @NotEmpty(message = "发送账号不能为空")
        private String carrier;
        //1==手机 2== 邮件
        @NotNull(message = "类型不能为空")
        private Integer type;
        //MsgEnum.MsgAll
        @NotEmpty(message = "发送场景不能为空")
        private String sceneType;
    }


}
