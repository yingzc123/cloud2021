package com.yzc.springcloud.stream.consume;


import com.yzc.springcloud.mystream.MySink01;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(MySink01.class)
public class MessageConsume01 {


    /**
     * 接收消息
     */
    @StreamListener(MySink01.DEFAULT_Msg)
    public void receive(String msg) {
        System.out.println("msg:"+msg);
    }
}
