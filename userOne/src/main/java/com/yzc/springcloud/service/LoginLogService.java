package com.yzc.springcloud.service;

import com.yzc.springcloud.entity.LoginLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yzc.springcloud.entity.User;
import com.yzc.springcloud.entity.dto.LoginDto;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yzc
 * @since 2021-09-02
 */
public interface LoginLogService extends IService<LoginLog> {
    //登录日志。未加token版
    void loginCheck(LoginDto.Login dto) throws Exception;
    User login(LoginDto.MsgAll msgAll) throws Exception;
    //发送消息
    void sendMsg(LoginDto.SendMsgCarrier sendMsgCarrier)  throws Exception ;
    //注册
    void register(LoginDto.MsgAll msgAll) throws Exception;
    //检查账号是否存在
    void checkNumberIsExist(Integer type,String sceneType,String carrier)throws Exception;
    //检查验证码
    void checkCode(String carrier,String sceneType,String code) throws Exception;
    //判断是手机还是邮箱返回User
    User byTypeReturnUser(Integer type,String carrier);


}
