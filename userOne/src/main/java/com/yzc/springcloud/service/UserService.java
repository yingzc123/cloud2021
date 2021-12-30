package com.yzc.springcloud.service;

import com.yzc.springcloud.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yzc.springcloud.entity.mqVo.UserPay;



/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yzc
 * @since 2021-08-08
 */
public interface UserService extends IService<User>  {

    String isOk(Integer id);
    String isNo(Integer id);
    String getCircuitBreaker(Integer id);
    String getRedis(String key);
    int checkIsExist(String number) throws Exception;
    void saveUser(User user) throws Exception;
    String  returnNumber();
    void  userSucceedOrder(UserPay userPay);


    void checkIsMsg() throws Exception;

}
