package com.yzc.springcloud.service;

public interface SendMsg {
    /**
     *
     * @param date 接收账号
     * @param sendType 场景 登录。。注册。。。
     * @param type 1短信 2邮件
     * @throws Exception
     */
    void send(String date, String sendType, Integer type) throws  Exception;

}
