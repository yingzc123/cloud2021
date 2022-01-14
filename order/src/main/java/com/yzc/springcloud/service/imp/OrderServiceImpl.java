package com.yzc.springcloud.service.imp;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yzc.springcloud.entity.Order;
import com.yzc.springcloud.dao.OrderMapper;
import com.yzc.springcloud.entity.dto.OrderDto;
import com.yzc.springcloud.entity.feignVo.User;
import com.yzc.springcloud.entity.mqVo.UserPay;
import com.yzc.springcloud.feign.UserFeign;
import com.yzc.springcloud.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzc.springcloud.utils.exceptionhandler.DiyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzc
 * @since 2021-08-07
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private AmqpTemplate rabbitTemplate;
    @Autowired
    private UserFeign userFeign;

    @Override
    @Transactional
    public Order userCreateOrder(OrderDto.CreateOrderUser dto) throws Exception{
        User user = userFeign.getUserId(dto.getUserId());
        if(null == user) {
            throw new DiyException(500,"用户不存在");
        }
        Order order = Order.builder().orderNo(returnOrderNo()).userId(dto.getUserId()).price(new BigDecimal(100)).goodsId
                (dto.getGoodsId()).goodsName(dto.getGoodsName()).build();
        System.out.println(order.getOrderNo());
        log.info(JSONUtil.toJsonStr(order));
        save(order);
        rabbitTemplate.convertAndSend("per_queue_ttl_exchange", "delay_queue_per_queue_ttl", order.getOrderNo());
        return order;
    }

    @Override
    @Transactional
    public void payOrder(OrderDto.PayOrderUser dto) throws Exception{
        Order order = getOne(new QueryWrapper<Order>().eq("order_no", dto.getOrderNo()));
        if(null == order) {
            throw new Exception("没有找到该订单");
        }
        if(1 == order.getIsPay()) {
            throw new Exception("订单已经支付");
        }
      /* User user = userFeign.getUserId(dto.getUserId());
        if(null == user) {
            throw new Exception("用户不存在");
        }
        if(user.getBalance().compareTo(order.getPrice()) == -1) {
            throw new Exception("用户余额不足");
        }*/
        Order orderNew=new Order();
        orderNew.setId(order.getId());
        orderNew.setIsPay(1);
        updateById(orderNew);
        /**
         *  为什么用消息队列： 异步，加修改别的库的东西基本不可能用远程调用，不安全，或者服务宕机丢失问题等等
         *  还会暴露调用地址 所以用消息队列偷偷异步去执行
         *  我的理解是这里是消息发送者
         */
        rabbitTemplate.convertAndSend("order_succeed", "", UserPay.builder()
                .userId(dto.getUserId()).price(order.getPrice()).build());
    }

    @Override
    public String returnOrderNo(){
        while (true) {
            String orderOn = RandomUtil.randomString(18).toUpperCase();
            int count = count(new QueryWrapper<Order>().eq("order_no", orderOn));
            if(0 == count) {
                return orderOn;
            }
        }
    }

    /**
     * 模拟用户下单在指定时间内未支付则删除订单
     */
    @Override
    public void delOrder(String orderNo) {
        Order one = getOne(new QueryWrapper<Order>().eq("order_no", orderNo).eq("is_del", 0));
        if(null == one) {
            log.error(orderNo+":订单不存在");
            return;
        }
        if(1 == one.getIsPay()) {
            log.error(orderNo+":订单已支付");
            return;
        }
        update(new UpdateWrapper<Order>().set("is_del",1).eq("order_no",orderNo));
    }


}
