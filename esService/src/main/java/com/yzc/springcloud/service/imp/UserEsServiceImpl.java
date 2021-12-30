package com.yzc.springcloud.service.imp;


import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;

import cn.hutool.json.JSONUtil;
import com.yzc.springcloud.dao.UserEsDao;
import com.yzc.springcloud.entity.User;
import com.yzc.springcloud.entity.esQuery.UserQuery;
import com.yzc.springcloud.service.UserEsService;
import com.yzc.springcloud.utils.exceptionhandler.DiyException;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class UserEsServiceImpl implements UserEsService {

    @Autowired
    private UserEsDao userEsDao;

    @Override
    public List<User> getListUser() {
        Iterable<User>  userList= userEsDao.findAll();
        JSONUtil.toJsonStr(userList);
        List<User> vo=new ArrayList<>();
        userList.forEach(date -> {
            vo.add(date);
        });
        return vo;
    }

    public Page<User> getPage(String name, Integer sub, Integer size){
        BoolQueryBuilder goodsQueryBuilder = QueryBuilders.boolQuery();
        if(StrUtil.isNotEmpty(name)) {
            goodsQueryBuilder.must(QueryBuilders.boolQuery().
                    must(QueryBuilders.termQuery("name", name)));
        }
        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(goodsQueryBuilder)
                .withPageable(PageRequest.of(sub, size)).build();
        return    userEsDao.search(build);
    }

    @Override
    public Page<User> getPage(UserQuery userQuery, Integer sub, Integer size) {
        NativeSearchQuery build = userQuery.getNativeSearchQueryBuilder()
                .withPageable(PageRequest.of(sub, size))
                .withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC))
                .build();
        Page<User> pageUser = userEsDao.search(build);
       return pageUser;
    }

    @Override
    public void save() {
       for (int i=2;i<2000;i++) {
            UUID uuid = UUID.randomUUID();
            userEsDao.save( User.builder().id(Long.valueOf(i)).name(uuid.toString().substring(0,3)).sex(i > 1000 ? "男" : "女").email(uuid.toString().substring(0,12)).
                    tel(uuid.toString().substring(0,10)).age(i).
                    balance(new BigDecimal(10000)).address("江西南昌").build());
        }
    }

    @Override
    public void testException() {
     throw  new DiyException(500,"纯纯sb");
    }
}
