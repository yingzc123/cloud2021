package com.yzc.springcloud.service.imp;


import cn.hutool.core.util.RandomUtil;
import com.yzc.springcloud.entity.enumAll.MsgEnum;
import com.yzc.springcloud.service.SendMsg;
import com.yzc.springcloud.utils.MsgUtil;
import com.yzc.springcloud.utils.RedisUtil;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SendMsgImpl implements SendMsg {
    @Autowired
    private MsgUtil msgUtil;
    @Autowired
    private RedisUtil redisUtil;


    /**
     * 适配器模式
     *
     * @param carrier   发送账号
     * @param sceneType 场景 登录。。注册。。。
     * @param type      1短信 2邮件
     * @throws Exception
     */

    @Override
    public void send(String carrier, String sceneType, Integer type) throws Exception {
        MsgEnum.MsgAll retrieval = MsgEnum.MsgAll.retrieval(sceneType);
        if (null == retrieval) {
            throw new Exception("此请求不被支持");
        }
        boolean isTrue = redisUtil.hasKey(carrier + retrieval.getValue());
        if (isTrue) {
            long time = redisUtil.getExpire(carrier + retrieval.getValue());
            throw new Exception("请"+time+"秒后在发送验证码请求");
        }
        String code = RandomUtil.randomString(6);
        if (1 == type) {
            msgUtil.sendSms(carrier, code);
        } else if (2 == type) {
            msgUtil.sendEmail(carrier, code);
        } else {
            throw new Exception("此请求不被支持");
        }
        redisUtil.set(carrier + retrieval.getValue(), code, 60);
    }


}
