package com.yzc.springcloud.mq;


import org.springframework.amqp.core.Message;
import com.yzc.springcloud.entity.mqVo.UserPay;
import com.yzc.springcloud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class UserMq {

    @Autowired
    private UserService userService;




    /**
     * 这里我的理解是 这里是消费者 这个地方会在orderController调用
     */
    @RabbitListener(queues = "order_succeed.user")
    public void userOrderSucceedAfter(UserPay userPay) {
        log.info("支付成功消费队列:{}", userPay);
        userService.userSucceedOrder(userPay);
    }


}
