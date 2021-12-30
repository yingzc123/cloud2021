package com.yzc.springcloud.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AliPayDto {
    @NotNull
    private String orderNo; //订单号
    @NotNull
    private String cancelUrl;//取消URl
    @NotNull
    private String successUrl;//成功地址
}
