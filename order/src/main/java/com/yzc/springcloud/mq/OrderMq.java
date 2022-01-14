package com.yzc.springcloud.mq;

import com.yzc.springcloud.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class OrderMq {
    @Resource
    private OrderService orderService;
    /**
     * 延时队列，下单之后多久如果不支付取消订单
     */
    @RabbitListener(queues = "delay_process_queue")
    public void DELAY_PROCESS_QUEUE_NAME(Message message) {
        String realMessage = new String(message.getBody());
        log.info("死信队列延时判断订单支付未支付队列，订单号:{}", realMessage);
        orderService.delOrder(realMessage);
    }
}
