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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {


    @Override
    public Page<RoleVO.RoleReturnVO> getList() {
        QueryWrapper<Role> qw=new QueryWrapper<>();
        Page<Role> page = page(new Page(1, 10), qw);
        Page<RoleVO.RoleReturnVO> voPage = new Page<>(page.getCurrent(), page.getSize());
        voPage.setRecords(BeanKit.copy(page.getRecords(), RoleVO.RoleReturnVO.class));
        return voPage;
    }

    public static void main(String[] args) {
        String str="[{\"in_stock_sn\":\"RK2112270019\",\"in_stock_time\":\"2021-12-27 19:41:22\",\"stockin_qty\":101,\"pur_sn\":\"RD798743\"}]";
        JSONArray jsonArray = JSONObject.parseArray(str);
        // 遍历jsons数组对象，
        for (int i = 0; i < jsonArray.size(); i++) {
            Object object = jsonArray.get(i);
            System.out.println(object.toString());
        }

    }

}
