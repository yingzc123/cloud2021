package com.yzc.springcloud.config.mqconfig;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    /******/
    @Bean
    public Queue orderSucceed(){return new Queue("order_succeed.user");} //模拟用户下单成功  扣用户余额
    @Bean
    FanoutExchange orderSucceedExchange(){return new FanoutExchange("order_succeed");}
    @Bean
    Binding bindingOrderSucceedExchangeAndOrderSucceed(Queue orderSucceed, FanoutExchange orderSucceedExchange){
        return BindingBuilder.bind(orderSucceed).to(orderSucceedExchange);
    }

/*******************************************************************************************************************************/
/*******************************************************************************************************************************/
/****延时队列*****/

}
