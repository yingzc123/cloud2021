package com.yzc.springcloud.service.imp;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzc.springcloud.entity.User;
import com.yzc.springcloud.dao.UserMapper;
import com.yzc.springcloud.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public void updateUser() {
        List<User> list = list(new QueryWrapper<User>().select("email", "sex"));
        log.info("值：{}",JSONUtil.parseArray(list));
        list.forEach(data ->{data.setAge(18);});
        try {
            boolean returnValue = updateBatchById(list);
        }catch (Exception e) {
            log.info("returnValue：{}",JSONUtil.toJsonStr(e.getMessage()));
        }

    }
}
