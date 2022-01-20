package com.yzc.springcloud.controller;


import com.alibaba.fastjson.JSON;
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

import javax.servlet.http.HttpServletResponse;
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
    public ResultObject setRedis(@RequestBody @Valid RoleDto.RoleByRedis dto) {
        log.info("role/setRedis:{}", JSONObject.toJSON(dto));
        roleService.addRedisKey(dto.getKey(), dto.getValue());
        return new ResultObject(200, "添加成功");
    }

    @PostMapping("/list")
    public ResultObject getList(@RequestBody RoleDto.RoleQuery dto) {
        log.info("role/list:{}", JSONObject.toJSON(dto));
        Page<RoleVO.RoleReturnVO> list = roleService.getList(dto);
        return new ResultObject(200, new PageTitle<>(list, RoleVO.RoleReturnVO.class));
    }

    @PostMapping("/listRole")
    public ResultObject getListRole(@RequestBody RoleDto.RoleQuery dto) {
        log.info("role/listRole:{}", JSONObject.toJSON(dto));
        return new ResultObject(200, roleService.getListRole(dto));
    }

    /**
     * 身份导出
     * @param dto
     * @param response
     */
    @GetMapping("/export")
    public void exportTmp(RoleDto.RoleQuery dto, HttpServletResponse response){
            log.info("role/export:" + JSON.toJSONString(dto));
        roleService.export(dto,response);

    }


    @PutMapping("/reNewOrDisable")
    public ResultObject reNewOrDisable(@RequestBody @Valid RoleDto.RoleReNewOrDisable dto) {
        log.info("role/reNewOrDisable:{}", JSONObject.toJSON(dto));
        roleService.reNewOrDisable(dto);
        return new ResultObject(200, "成功");
    }

    @GetMapping("/to")
    public void to() {
        messageProducer01.send("hahahahah");
    }





}
