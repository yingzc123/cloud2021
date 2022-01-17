package com.yzc.springcloud.utils.exceptionhandler;


import cn.hutool.json.JSONUtil;
import com.yzc.springcloud.entity.ResultObject;
import com.yzc.springcloud.myenum.ResultCode;
import com.yzc.springcloud.utils.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody //为了返回数据
    public ResultObject error(Exception e) {
        e.printStackTrace();
        return ResultObject.error().message("执行了全局异常处理.." + e.getMessage());
    }

    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody //为了返回数据
    public ResultObject error(ArithmeticException e) {
        e.printStackTrace();
        return ResultObject.error().message("执行了ArithmeticException异常处理..");
    }

    //自定义异常
    @ExceptionHandler(DiyException.class)
    @ResponseBody //为了返回数据
    public ResultObject error(DiyException e) {
        e.printStackTrace();
        return ResultObject.error().code(e.getCode()).message(e.getMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody //为了返回数据
    public ResultObject MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        log.info("e:{}", JSONUtil.toJsonStr(e));
        log.info("objectError:{}", JSONUtil.toJsonStr(objectError));
        // 然后提取错误提示信息进行返回
        return ResultObject.error().code(ResultCode.ERROR.getCode()).message(objectError.getDefaultMessage());
    }

}
