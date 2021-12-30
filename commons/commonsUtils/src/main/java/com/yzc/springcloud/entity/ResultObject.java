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
