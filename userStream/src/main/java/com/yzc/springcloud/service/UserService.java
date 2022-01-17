package com.yzc.springcloud.service;

import com.yzc.springcloud.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yzc
 * @since 2022-01-17
 */
public interface UserService extends IService<User> {
    void updateUser(String userId,String email);
    User checkIsExist(String userId);
}
