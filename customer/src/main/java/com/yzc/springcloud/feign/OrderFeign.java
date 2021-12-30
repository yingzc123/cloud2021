package com.yzc.springcloud.feign;

import com.yzc.springcloud.entity.feignVo.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("cloud-order-service")
public interface OrderFeign {

    @GetMapping("/order/getPort")
    String getPort();

    @GetMapping("/order/order/getOrderNo")
    Order getOrderByOrderNo(String orderNo);
}
