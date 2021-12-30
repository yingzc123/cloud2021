package com.yzc.springcloud.service.imp;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yzc.springcloud.entity.User;
import com.yzc.springcloud.dao.UserMapper;
import com.yzc.springcloud.entity.mqVo.UserPay;
import com.yzc.springcloud.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzc.springcloud.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzc
 * @since 2021-08-08
 */
@Service
//@DefaultProperties(defaultFallback = "is_not_return")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String isOk(Integer id) {
            return "线程池："+Thread.currentThread().getName()+"isOk:"+id;
    }

    @Override
    @HystrixCommand(fallbackMethod = "is_not_return",commandProperties = {
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    //@HystrixCommand
    public String isNo(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+"isNo:"+id;
    }

    public String is_not_return(Integer id) {
       return "服务异常";
    }

    @Override
    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),   //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),  //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),    //时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),    //失败率达到多少后跳闸
    })
    public String getCircuitBreaker(Integer id){
        if(id < 0){
            throw new RuntimeException("******id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();  //UUID.randomUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+serialNumber;
    }

    @Override
    public String getRedis(String key) {
        Object o = redisUtil.get(key);
        return o.toString();
    }

    @Override
    public int checkIsExist(String number) throws Exception {
      return   count(new QueryWrapper<User>().eq("user_number",number));
    }

    @Override
    public void saveUser(User user) throws Exception{
        if(null == user ) {
            throw new Exception("参数有误，注册失败");
        }
        user.setUserNumber(returnNumber());
        user.setUserName("新用户");
        save(user);
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能负数，请稍后再试，o(╥﹏╥)o  id："+id;
    }

    @Override
    public String  returnNumber(){
        while (true) {
            String number = RandomUtil.randomString(10).toUpperCase();
            int count = count(new QueryWrapper<User>().eq("user_number", number));
            if(0 == count) {
                return number;
            }
        }
    }

    /**
     *
     * @param userPay
     * 下单成功扣余额
     */
    @Override
    public void userSucceedOrder(UserPay userPay) {
        User user = getById(userPay.getUserId());
        user.setBalance(user.getBalance().subtract(userPay.getPrice()));
        updateById(user);
    }

    @Override
    public void checkIsMsg() throws Exception {
        List<User> list = list();
        list.forEach(date -> {
            try{
                int i = 10 / 0;
            }catch (Exception e) {
                throw e;
            }
        });

    }

    void testCheck() throws Exception{
        try{
            int i = 10 / 0;
        }catch (Exception e) {
            try {
                e.printStackTrace();
            }catch (Exception es) {
                throw   es;
            }

        }
    }


}
