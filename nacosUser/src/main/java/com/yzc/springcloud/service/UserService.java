package com.yzc.springcloud.service;

import com.yzc.springcloud.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yzc
 * @since 2021-11-05
 */
public interface UserService extends IService<User> {
    void updateUser() throws Exception;
}
