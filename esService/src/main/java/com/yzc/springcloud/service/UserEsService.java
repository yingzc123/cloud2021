package com.yzc.springcloud.service;

import com.yzc.springcloud.entity.User;
import com.yzc.springcloud.entity.esQuery.UserQuery;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface UserEsService {
    List<User> getListUser() ;
    Page<User> getPage(UserQuery userQuery, Integer sub, Integer size) ;
    void save();
    void  testException();
}
