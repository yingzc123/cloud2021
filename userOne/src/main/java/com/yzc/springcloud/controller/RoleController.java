package com.yzc.springcloud.controller;


import com.yzc.springcloud.entity.ResultObject;
import com.yzc.springcloud.entity.Role;
import com.yzc.springcloud.service.RoleService;
import com.yzc.springcloud.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yzc
 * @since 2021-09-06
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RedisUtil redisUtils;

    @GetMapping("/getList")
    public ResultObject returnRoleList() {
        List<Role> list = roleService.list();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (true) {
            i++;
            sb.append("爱你爱你");
            if (1000 == i)
                break;
        }
        return new ResultObject(200, "查询成功", sb);
       /* List<Object> list = redisUtils.lGet("test", 0, 10);
       if (null != list && list.size() > 0) {
            for (Object o : list) {
                Role role= (Role) o;
                if (1 == role.getRoleId()) {
                    redisUtils.lRemove("trial_goods_hide", 1, o);
                }
            }
        }*/
    }

}
