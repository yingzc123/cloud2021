package com.yzc.springcloud.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yzc.springcloud.entity.User;

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

  IPage<User> getUserList(Integer index, Integer size);
}
