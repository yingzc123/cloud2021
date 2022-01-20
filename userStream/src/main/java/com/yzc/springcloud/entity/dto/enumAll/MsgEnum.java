package com.yzc.springcloud.entity.dto.enumAll;

import lombok.Getter;

public class MsgEnum {
    public enum MsgAll {

        register("register","REGISTER"),//注册
        login("login","LOGIN"),//登录
        update_pwd("update_pwd","UPDATEPWD");//修改密码
        @Getter
        private String code;
        @Getter
        private String value;

        MsgAll(String code, String value) {
            this.code = code;
            this.value = value;
        }

        public static MsgEnum.MsgAll retrieval(String code) {
            for (MsgEnum.MsgAll key : values()) {
                if (key.getCode() .equals(code) ) {
                    return key;
                }
            }
            return null;
        }
    }
}
