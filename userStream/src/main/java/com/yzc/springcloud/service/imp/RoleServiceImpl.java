package com.yzc.springcloud.service.imp;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzc.springcloud.entity.Role;
import com.yzc.springcloud.dao.RoleMapper;
import com.yzc.springcloud.entity.dto.RoleDto;
import com.yzc.springcloud.entity.vo.RoleVO;
import com.yzc.springcloud.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzc.springcloud.utils.BeanKit;
import com.yzc.springcloud.utils.EntityUtils;
import com.yzc.springcloud.utils.ExportCvsUtil;
import com.yzc.springcloud.utils.RedisUtil;
import com.yzc.springcloud.utils.exceptionhandler.DiyException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
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
        if (StringUtils.isEmpty(key)) {
            throw new DiyException(500, "key不可以为null");
        }
        boolean isTrue = redisUtil.hasKey(key);
        if (!isTrue) {
            throw new DiyException(500, "key不存在");
        }
        return redisUtil.get(key);
    }

    @Override
    public void addRedisKey(String key, String value) {
        boolean isTrue = redisUtil.hasKey(key);
        if (isTrue) {
            throw new DiyException(500, "key已经存在添加失败");
        }
        boolean addIsTrue = redisUtil.set(key, value);
        if (!addIsTrue) {
            throw new DiyException(500, "未知错误添加失败");
        }

    }

    @Override
    public Page<RoleVO.RoleReturnVO> getList(RoleDto.RoleQuery dto) {
        Page<Role> page = page(new Page(dto.getCurrent(), dto.getSize()), dto.queryWrapper());
        Page<RoleVO.RoleReturnVO> voPage = new Page<>(page.getCurrent(), page.getSize());
        voPage.setRecords(BeanKit.copy(page.getRecords(), RoleVO.RoleReturnVO.class));
        return voPage;
    }

    @Override
    public  Page<Role>  getListRole(RoleDto.RoleQuery dto) {
        return page(new Page(dto.getCurrent(), dto.getSize()), dto.queryWrapper());
    }

    @Override
    public void export(RoleDto.RoleQuery dto, HttpServletResponse response)  {
        List<Role> list = list(dto.queryWrapper());
        try {
            ExportCvsUtil.setExportCvs("身份列表", response);
            if(CollectionUtil.isEmpty(list)) {
                new DiyException(500,"导出列表为空");
            }
            EasyExcel.write(response.getOutputStream(), RoleVO.RoleReturnVO.class).sheet
                    ("身份列表").doWrite(EntityUtils.transfer(list, RoleVO.RoleReturnVO.class));
        }catch (Exception e) {
            log.info(e.getMessage());
            throw new DiyException(500,e.getMessage());
        }

    }


    @Override
    public void updateRole(Role role) {
        BeanUtils.copyProperties(Role.builder().roleId(888).roleName("帅哥").state("2").build(), role);
    }

    @Override
    public void updateJson(JSONObject jsonObject) {
        JSONObject returnObject = null;
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
        log.info("role:{}", JSONObject.toJSON(role));
        log.info("jsonObject:{}", jsonObject);
    }

    @Override
    public void reNewOrDisable(RoleDto.RoleReNewOrDisable dto) {
        int count = count(new QueryWrapper<Role>().eq("role_id", dto.getRoleId()));
        if(0 == count) {
            new DiyException(500,"未找到该身份");
        }
        update(new UpdateWrapper<Role>().set("state",dto.getState()).eq("role_id",dto.getRoleId()));
    }

    @Override
    public void popRedis(String key) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 5, 2L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

        while (true) {
            //redisUtil.lPush(key,UUID.randomUUID().toString().substring(0,4));
           Object pop = redisUtil.lPop(key);
            log.info("pop:{}", JSON.toJSONString(pop));
        }

       // log.info("pop:{}", JSON.toJSONString(pop));

    }

}
