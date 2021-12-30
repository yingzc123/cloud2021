package com.yzc.springcloud.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yzc.springcloud.entity.User;
import com.yzc.springcloud.entity.dto.UserDto;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yzc
 * @since 2021-12-02
 */
public interface UserService extends IService<User> {
    void addUser();

    Long saveUser(int size, int batch);

    List<User> getUserList(UserDto.SelectUser selectUser);

    void test();
}
