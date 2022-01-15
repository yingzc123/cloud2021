package com.yzc.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


@Data
@AllArgsConstructor
public class ResultObject<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;
    private boolean success;

    //把构造方法私有
    private ResultObject() {}
    //成功静态方法
    public static ResultObject ok() {
        ResultObject r = new ResultObject();
        r.setSuccess(true);
        r.setCode(200);
        r.setMessage("成功");
        return r;
    }

    //失败静态方法
    public static ResultObject error() {
        ResultObject r = new ResultObject();
        r.setSuccess(false);
        r.setCode(500);
        r.setMessage("失败");
        return r;
    }
    public ResultObject success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public ResultObject message(String message){
        this.setMessage(message);
        return this;
    }

    public ResultObject code(Integer code){
        this.setCode(code);
        return this;
    }


    public ResultObject(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
        this.success = true;
    }

    public ResultObject(int code, T data) {
        this.code = code;
        this.message = "";
        this.data = data;
        this.success = true;
    }


    public ResultObject(int code, String message,T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = true;
    }
}
