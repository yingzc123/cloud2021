package com.yzc.springcloud.service.imp;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzc.springcloud.entity.EmailBd;
import com.yzc.springcloud.dao.EmailBdMapper;
import com.yzc.springcloud.entity.User;
import com.yzc.springcloud.service.EmailBdService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzc.springcloud.service.TestOneService;
import com.yzc.springcloud.service.TestTwoService;
import com.yzc.springcloud.service.UserService;
import com.yzc.springcloud.utils.exceptionhandler.DiyException;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzc
 * @since 2021-11-05
 */
@Service
@Slf4j
public class EmailBdServiceImpl extends ServiceImpl<EmailBdMapper, EmailBd> implements EmailBdService {
    @Autowired
    private UserService userService;
    @Resource
    private RedissonClient redissonClient;
    @Resource
    private TestTwoService testTwoService;
    @Resource
    private TestOneService testOneService;

    @Override
    public String returnSave(String email) {
        RLock lock = redissonClient.getLock(email);
        try {
            if(lock.tryLock(0,3000, TimeUnit.MILLISECONDS)){
                Thread.sleep(100000);
                log.info("进来了：{}",System.currentTimeMillis()/1000);
            User user = userService.getOne(new QueryWrapper<User>().eq("email", email));
            if(null == user) {
               throw  new DiyException(500,"没有邮箱为"+email+"的用户");
            }
            int count = count(new QueryWrapper<EmailBd>().eq("email_key", email));
            if(count > 0) {
                throw  new DiyException(500,"一个用户只能绑定一次");
            }
            save(EmailBd.builder().emailKey(email).isDel(0).createTime(new Date()).build());
            Thread.sleep(2000);
            }else {
                throw new DiyException(500,"系统繁忙稍后再试");
            }
        }catch (Exception e) {
            throw  new DiyException(500,JSONUtil.parseObj(e).get("msg").toString());
        }finally {
            if(lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return "成功";
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveTest() {
        testOneService.addTransactionalOne();
        testTwoService.addTransactionalTwo();
    }

}
