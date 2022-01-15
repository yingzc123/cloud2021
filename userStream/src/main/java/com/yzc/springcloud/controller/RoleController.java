package com.yzc.springcloud.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzc.springcloud.config.common.PageTitle;
import com.yzc.springcloud.entity.ResultObject;
import com.yzc.springcloud.entity.Role;
import com.yzc.springcloud.entity.dto.RoleDto;
import com.yzc.springcloud.entity.vo.RoleVO;
import com.yzc.springcloud.service.RoleService;
import com.yzc.springcloud.stream.producer.MessageProducer01;
import com.yzc.springcloud.utils.RedisUtil;
import com.yzc.springcloud.utils.exceptionhandler.DiyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yzc
 * @since 2021-12-24
 */
@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController {

    @Autowired
    private MessageProducer01 messageProducer01;

    @Autowired
    private RoleService roleService;


    @GetMapping("/getRedisObject")
    public ResultObject getRedisObject(String key) {
        Object o = roleService.testRedisUtils(key);
        return new ResultObject(200, o);
    }

    @PostMapping("/setRedis")
    public ResultObject setRedis(@RequestBody @Valid RoleDto.RoleByRedis dto, BindingResult result) {
        log.info("role/setRedis:{}", JSONObject.toJSON(dto));
        if (result.hasErrors()) {
            throw new DiyException(500, result.getAllErrors().get(0).getDefaultMessage());
        }
        roleService.addRedisKey(dto.getKey(), dto.getValue());
        return new ResultObject(200, "添加成功");
    }

    @GetMapping("/list")
    public ResultObject getList() {
        Page<RoleVO.RoleReturnVO> list = roleService.getList();
        return new ResultObject(200, new PageTitle<>(list, RoleVO.RoleReturnVO.class));
    }

    @GetMapping("/to")
    public void to() {
        messageProducer01.send("hahahahah");
    }


}
