package com.yzc.springcloud.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class OrderDto {

    @Data
    public static class PayOrderUser{
        //用户id
        @NotEmpty(message = "用户ID不能为空")
       private String userId;
       //订单号
       @NotEmpty(message = "订单号不能为空")
       private String orderNo;
    }

    @Data
    public static class CreateOrderUser{
        //用户id
        @NotEmpty(message = "用户ID不能为空")
        private String userId;
        //商品Id
        @NotNull(message = "订单号不能为空")
        private Integer goodsId;
        //商品名称
        @NotEmpty(message = "订单号不能为空")
        private String goodsName;

    }
}
