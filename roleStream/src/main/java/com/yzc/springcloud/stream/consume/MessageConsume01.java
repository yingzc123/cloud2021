package com.yzc.springcloud.stream.consume;


import com.yzc.springcloud.mystream.MySink01;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(MySink01.class)
@Slf4j
public class MessageConsume01 {


    /**
     * 接收消息
     */
    @StreamListener(MySink01.DEFAULT_Msg)
    public void receive(String msg) {
        log.info("来自stream的普通消息:{}",msg);
        System.out.println("msg:"+msg);
    }

    /**
     * 接收消息
     */
    @StreamListener(MySink01.TO_MSG)
    public void toMsg(String msg) {
        log.info("来自stream的延时消息:{}",msg);
        System.out.println("TO_MSG:"+msg);
    }
}
