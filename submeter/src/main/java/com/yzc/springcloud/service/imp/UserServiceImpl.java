package com.yzc.springcloud.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzc.springcloud.dao.UserMapper;
import com.yzc.springcloud.entity.User;
import com.yzc.springcloud.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yzc
 * @since 2021-12-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Value("${spring.shardingsphere.sharding.tables.user.table-strategy.inline.algorithm-expression}")
    String value;

    @Override
    public void addUser() {
        System.out.println("-----------" + value);
        for (int i = 1; i < 10; i++) {
            User user = new User();
            user.setUserName("admin");
            if (i % 2 == 0) {
                user.setSex("外星人");
            } else {
                user.setSex("野人");
            }
            user.setAge(i);
            save(user);
        }
    }

    @Override
    public IPage<User> getUserList(Integer index,Integer size) {
      return page(new Page<>(index,size), new QueryWrapper<User>().eq("user_name", "爷爷"));

    }
}
