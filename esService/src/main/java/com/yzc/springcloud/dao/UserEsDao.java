package com.yzc.springcloud.dao;

import com.yzc.springcloud.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;



@Component
public interface UserEsDao extends ElasticsearchRepository<User,Long> {


}
