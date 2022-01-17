package com.yzc.springcloud.service.imp;

import com.yzc.springcloud.entity.User;
import com.yzc.springcloud.dao.UserMapper;
import com.yzc.springcloud.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzc.springcloud.utils.exceptionhandler.DiyException;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzc
 * @since 2022-01-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public void updateUser(String userId, String email) {
        User user = checkIsExist(userId);
        user.setEmail(email);
        updateById(user);
    }

    @Override
    public User checkIsExist(String userId) {
        User user = getById(userId);
        if(null == user) {
            throw new DiyException(500,"用户不存在");
        }
        return user;
    }
}
