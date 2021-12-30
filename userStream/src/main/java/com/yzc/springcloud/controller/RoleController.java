package com.yzc.springcloud.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzc.springcloud.config.common.PageTitle;
import com.yzc.springcloud.entity.ResultObject;
import com.yzc.springcloud.entity.Role;
import com.yzc.springcloud.entity.vo.RoleVO;
import com.yzc.springcloud.service.RoleService;
import com.yzc.springcloud.stream.producer.MessageProducer01;
import com.yzc.springcloud.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yzc
 * @since 2021-12-24
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private MessageProducer01 messageProducer01;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/list")
    public ResultObject getList(){
        Page<RoleVO.RoleReturnVO> list = roleService.getList();
        return  new ResultObject(200, new PageTitle<>(list, RoleVO.RoleReturnVO.class));
    }

    @GetMapping("/to")
    public void to(){
        messageProducer01.send("hahahahah");
    }


    @GetMapping("/setRedis")
    public void setRedis(){
        redisUtil.set("yzc","大帅哥");
    }


    @GetMapping("/getRedis")
    public Object getRedis(){
      return   redisUtil.get("yzc");
    }
}
