package com.yzc.springcloud.service.imp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzc.springcloud.entity.Role;
import com.yzc.springcloud.dao.RoleMapper;
import com.yzc.springcloud.entity.vo.RoleVO;
import com.yzc.springcloud.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzc.springcloud.utils.BeanKit;
import com.yzc.springcloud.utils.RedisUtil;
import com.yzc.springcloud.utils.exceptionhandler.DiyException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzc
 * @since 2021-12-24
 */
@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RedisUtil redisUtil;


    @Override
    public Object testRedisUtils(String key) {
        if(StringUtils.isEmpty(key)) {
          throw   new DiyException(500,"key不可以为null");
        }
        boolean isTrue = redisUtil.hasKey(key);
        if(!isTrue) {
            throw   new DiyException(500,"key不存在");
        }
        return redisUtil.get(key);
    }

    @Override
    public Page<RoleVO.RoleReturnVO> getList() {
        QueryWrapper<Role> qw=new QueryWrapper<>();
        Page<Role> page = page(new Page(1, 10), qw);
        Page<RoleVO.RoleReturnVO> voPage = new Page<>(page.getCurrent(), page.getSize());
        voPage.setRecords(BeanKit.copy(page.getRecords(), RoleVO.RoleReturnVO.class));
        return voPage;
    }

    @Override
    public void updateRole(Role role) {
        BeanUtils.copyProperties(Role.builder().roleId(888).roleName("帅哥").state("2").build(),role);
    }

    @Override
    public void updateJson(JSONObject jsonObject) {
        JSONObject returnObject=null;
        String str = "[{\"in_stock_sn\":\"RK2112270019\",\"in_stock_time\":\"2021-12-27 19:41:22\",\"stockin_qty\":101,\"pur_sn\":\"RD798743\"}]";
        JSONArray jsonArray = JSONObject.parseArray(str);
        returnObject = jsonArray.getJSONObject(0);
        jsonObject.putAll(returnObject);
    }

    @Override
    public void mainModer() {
        Role role = new Role();
        JSONObject jsonObject = new JSONObject();
        updateRole(role);
        updateJson(jsonObject);
        log.info("role:{}",JSONObject.toJSON(role));
        log.info("jsonObject:{}",jsonObject);
    }

}
