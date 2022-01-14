package com.yzc.springcloud.service;

import com.yzc.springcloud.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yzc.springcloud.entity.dto.OrderDto;



/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yzc
 * @since 2021-08-07
 */
public interface OrderService extends IService<Order> {

    Order userCreateOrder(OrderDto.CreateOrderUser dto);

    void payOrder(OrderDto.PayOrderUser dto) throws Exception;

    String returnOrderNo();
    void delOrder(String orderNo);

}
