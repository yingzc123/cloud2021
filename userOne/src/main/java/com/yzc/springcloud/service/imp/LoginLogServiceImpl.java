package com.yzc.springcloud.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzc.springcloud.entity.LoginLog;
import com.yzc.springcloud.dao.LoginLogMapper;
import com.yzc.springcloud.entity.User;
import com.yzc.springcloud.entity.dto.LoginDto;
import com.yzc.springcloud.entity.enumAll.MsgEnum;
import com.yzc.springcloud.service.LoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzc.springcloud.service.SendMsg;
import com.yzc.springcloud.service.UserService;
import com.yzc.springcloud.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yzc
 * @since 2021-09-02
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private SendMsg sendMsg;

    @Override
    @Transactional
    public void loginCheck(LoginDto.Login dto) throws Exception {
        int isExist = userService.checkIsExist(dto.getUserNum());
        if (0 == isExist) {
            throw new Exception("用户不存在");
        }
        long register = redisUtil.register(dto.getUserNum());
        if (register > 5) {
            throw new Exception("登录频繁，5分钟内限制登录5次");
        }
        LoginLog builder = LoginLog.builder().ip(dto.getIp()).userNum(dto.getUserNum()).logTime
                (new Date()).build();
        if (null == builder) {
            throw new Exception("参数有误，登入失败");
        }
        save(builder);
    }


    /**
     * @throws Exception
     * 发送消息：1短信，2邮箱
     */
    @Override
    public void sendMsg(LoginDto.SendMsgCarrier sendMsgCarrier) throws Exception {
        //判断账号是否被注册过
        checkNumberIsExist(sendMsgCarrier.getType(),sendMsgCarrier.getSceneType(),sendMsgCarrier.getCarrier());
        sendMsg.send(sendMsgCarrier.getCarrier(),sendMsgCarrier.getSceneType(),sendMsgCarrier.getType());
    }

    /**
     * 登入 弱智版
     * @param msgAll
     * @return
     * @throws Exception
     */
    @Override
    public User login(LoginDto.MsgAll msgAll) throws Exception {
        checkCode(msgAll.getCarrier(),msgAll.getSceneType(),msgAll.getCode());
        User user = byTypeReturnUser(msgAll.getType(), msgAll.getCarrier());
        if(null == user) {
            throw new Exception("用户不存在");
        }
        return user;
    }

    /**
     * 检查是否可已注册
     * @param msgAll
     * @throws Exception
     */
    @Override
    public void register(LoginDto.MsgAll msgAll) throws Exception {
        checkNumberIsExist(msgAll.getType(),msgAll.getSceneType(),msgAll.getCarrier());
        checkCode(msgAll.getCarrier(),msgAll.getSceneType(),msgAll.getCode());
        User user = new User();
        if (1 == msgAll.getType()) {
            user.setPhone(msgAll.getCarrier());
        } else if (2 == msgAll.getType()) {
            user.setEmail(msgAll.getCarrier());
        } else {
            throw new Exception("类型参数不合法");
        }
        userService.saveUser(user);
    }
    //验证验证码是否正确
    @Override
    public void checkCode(String carrier,String sceneType,String code) throws Exception {
        MsgEnum.MsgAll retrieval = MsgEnum.MsgAll.retrieval(sceneType);
        if(null == retrieval) {
            throw new Exception("场景参数不合法");
        }
        Object object = redisUtil.get(carrier + retrieval.getValue());
        if (null == object) {
            throw new Exception("请重新获取验证码");
        }
        if (!code.equals(object.toString())) {
            throw new Exception("验证码错误");
        }
    }

    /**
     *
     * @param type 1.手机，2邮件
     * @param carrier 账号 抽象化
     * @return
     */
    @Override
    public User byTypeReturnUser(Integer type, String carrier) {
        if (1 == type) {
            return userService.getOne(new QueryWrapper<User>().eq("phone",carrier));
        }
        else if (2 == type ){
            return userService.getOne(new QueryWrapper<User>().eq("email",carrier));
        }
        return null;
    }

    //判断账号是否存在
    @Override
    public void checkNumberIsExist(Integer type, String sceneType, String carrier) throws Exception {
        //获取枚举值
        MsgEnum.MsgAll retrieval = MsgEnum.MsgAll.retrieval(sceneType);
        if (null == retrieval) {
            throw new Exception("此请求不被支持");
        }
        if (1 == type) {
            int phone = userService.count(new QueryWrapper<User>().eq("phone", carrier));
            if (retrieval.getValue().equals("REGISTER")) {
                if (phone > 0) {
                    throw new Exception("该手机号已经注册过");
                }
            }else if(retrieval.getValue().equals("LOGIN")) {
               if(0 == phone) {
                   throw new Exception("该手机号请先注册");
               }
            }

        } else if (2 == type) {
            int email = userService.count(new QueryWrapper<User>().eq("email", carrier));
            if (retrieval.getValue().equals("REGISTER")) {
                if (email > 0) {
                    throw new Exception("该邮箱已经注册过");
                }
            }
            else if(retrieval.getValue().equals("LOGIN")) {
                if(0 == email) {
                    throw new Exception("该邮箱请先注册");
                }
            }
        } else {
            throw new Exception("参数不合法");
        }

    }


}
