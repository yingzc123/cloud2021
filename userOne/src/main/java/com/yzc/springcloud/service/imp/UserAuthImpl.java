/*
package com.yzc.springcloud.service.imp;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzc.springcloud.entity.User;
import com.yzc.springcloud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.yzc.springcloud.security.entity.SecurityUser;


import java.util.ArrayList;


@Component("userDetailsService")
@Slf4j
public class UserAuthImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userNumber) throws UsernameNotFoundException {
        User user = userService.getOne(new QueryWrapper<User>().eq("user_number", userNumber));
        //判断
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        com.yzc.springcloud.security.entity.User curUser = new com.yzc.springcloud.security.entity.User();
        BeanUtils.copyProperties(user, curUser);
        curUser.setToken("123456");
        //根据用户查询用户权限列表
        SecurityUser securityUser = new SecurityUser();
        securityUser.setCurrentUserInfo(curUser);
        securityUser.setPermissionValueList(new ArrayList<String>());
        log.info("securityUser:{}", JSONUtil.toJsonStr(securityUser));
        return securityUser;

    }
}
*/
