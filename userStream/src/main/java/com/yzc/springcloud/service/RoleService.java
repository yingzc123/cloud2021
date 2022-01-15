package com.yzc.springcloud.service;

import com.alibaba.fastjson.JSONObject;
import com.yzc.springcloud.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yzc.springcloud.entity.vo.RoleVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yzc
 * @since 2021-12-24
 */
public interface RoleService extends IService<Role> {

    Object testRedisUtils(String key);

    void addRedisKey(String key, String value);

    Page<RoleVO.RoleReturnVO> getList();

    void updateRole(Role role);

    void updateJson(JSONObject jsonObject);

    void mainModer();

}
