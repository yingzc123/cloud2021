package com.yzc.springcloud.controller;



import com.alibaba.fastjson.JSONObject;
import com.yzc.springcloud.entity.ResultObject;
import com.yzc.springcloud.entity.dto.LoginDto;
import com.yzc.springcloud.service.LoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yzc
 * @since 2021-09-02
 */
@RestController
@RequestMapping("/login")
@Slf4j
public class LoginLogController {

    @Autowired
    private LoginLogService loginLogService;


    @PostMapping("/loginLog")
    public ResultObject loginLog(@RequestBody  @Valid LoginDto.Login dto){
        try{
            loginLogService.loginCheck(dto);
            return new ResultObject(200,"登入记录成功");
        }
        catch (Exception e) {
            return new ResultObject(500,e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResultObject login(@RequestBody  @Valid LoginDto.MsgAll msgAll ){
        try{

            return new ResultObject(200,"登入成功",loginLogService.login(msgAll));
        }
        catch (Exception e) {
            return new ResultObject(500,e.getMessage());
        }
    }

    /**
     * 账号密码登入
     * @param loginLoginPwd
     * @return
     */
    @PostMapping("/loginPwd")
    public ResultObject loginPwd(@RequestBody  @Valid LoginDto.LoginPwd loginLoginPwd ){
        log.info("/login/loginPwd", JSONObject.toJSON(loginLoginPwd));
            return new ResultObject(200,"登入成功",loginLogService.login(loginLoginPwd));
    }


    @PostMapping("/sendMsg")
    public ResultObject sendEmail(@RequestBody  @Valid LoginDto.SendMsgCarrier sendMsgCarrier){
        try{
            loginLogService.sendMsg(sendMsgCarrier);
            return  new ResultObject(200,"发送成功");
        }
        catch (Exception e) {
            return new ResultObject(500,e.getMessage());
        }
    }


    @PostMapping("/register")
    public ResultObject register(@RequestBody  @Valid LoginDto.MsgAll dto ){
        try{
            loginLogService.register(dto);
            return new ResultObject(200,"注册成功");
        }
        catch (Exception e) {
            return new ResultObject(500,e.getMessage());
        }
    }







}
